package org.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admin.service.MemberService;
import org.admin.service.RentalService;
import org.admin.util.ReportType;
import org.admin.domain.Report;
import org.admin.service.RentalReportService;
import org.admin.service.TextReportService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@Slf4j
public class ReportManageController {
    private final RentalReportService rentalReportService;
    private final TextReportService textReportService;
    private final MemberService memberService;
    private final RentalService rentalService;

    @GetMapping("/list/{menu}")
    public RestResult<?> reportList(@PathVariable("menu") int menu) {
        log.debug("관리자 - report/list");
        switch (menu) {
            case 1:
                return RestResult.success(rentalReportService.getAll());
            case 2:
                return RestResult.success(textReportService.getAllBy(ReportType.BOARD.getValue()));
            case 3:
                // 댓글과 답글리스트를 합쳐서 model에 담는다.
                List<Report> comments = textReportService.getAllBy(ReportType.COMMENT.getValue());
                List<Report> replies = textReportService.getAllBy(ReportType.REPLY.getValue());
                comments.addAll(replies);
                return RestResult.success(comments);
            default:
                return RestResult.error("유효하지 않은 menu 입니다.");
        }
    }


    @GetMapping("/view/{targetNo}/{type}/{mno}")
    public RestResult<?> textReportView(@PathVariable int targetNo,
                                        @PathVariable String type,
                                        @PathVariable int mno) {
        return RestResult.success(textReportService.getBy(type, targetNo, mno));
    }

    @GetMapping("/view/{rentalNo}/{memberNo}")
    public RestResult<?> rentalReportView(@PathVariable int rentalNo,
                                          @PathVariable int memberNo) {
        return RestResult.success(rentalReportService.get(rentalNo, memberNo));
    }

    @Transactional
    @PutMapping("/update/{result}/{reportType}/{writerNo}")
    public RestResult<?> updateReport(@PathVariable String result, // 0-무시, 1-영구정지, 2-경고조치
                                      @PathVariable String reportType,
                                      @PathVariable int writerNo,
                                      @RequestBody Report report) {
        //게시물, 댓글, 대댓글 신고
        if (reportType.equals("0")) {
            //신고에 대한 상태를 완료로 변경
            textReportService.updateState(report.getReportNo());
            //사용자의 경고횟수 증가
            memberService.updateWarningCountBy(report.getReportNo());
            //신고건(게시물 or 댓글 or 답글)의 결과 처리
            textReportService.updateBoardState(report, result);
            return RestResult.success();
            //숙소 신고
        } else {
            //신고에 대한 상태를 완료로 변경
            rentalReportService.updateState(report.getTargetNo(), writerNo);
            //사용자(호스트)의 경고횟수 증가
            memberService.updateWarningCount(writerNo);
            //신고건(숙소)의 결과 처리
            rentalService.updateState(report.getTargetNo(), reportType);
            return RestResult.success();
        }
    }


}
