package org.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admin.domain.Member;
import org.admin.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberManageController {

    private final MemberService memberService;
    @GetMapping("/list/{menu}")
    public RestResult<?> memberList(@PathVariable int menu) {
        try {
            switch (menu) {
                case 1:
                    return RestResult.success(memberService.getAll());
                case 2:
                    return RestResult.success(memberService.getAllHosts());
                default:
                    return RestResult.error("유효하지 않은 menu 입니다.");
            }
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }

    }


    @GetMapping("/view/{memberNo}/{menu}")
    public RestResult<?> memberView(@PathVariable long memberNo,
                             @PathVariable int menu) {
        try {
            switch (menu) {
                case 1:
                    return RestResult.success(memberService.getMemberBy(memberNo));
                case 2:
                    return RestResult.success(memberService.getHostBy(memberNo));
                default:
                    return RestResult.error("유효하지 않은 menu 입니다.");
            }
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }


    }

    @GetMapping("/search/{keyword}/{filter}/{menu}")
    public RestResult<?> searchMember(@PathVariable String keyword,
                                         @PathVariable String filter,
                                         @PathVariable int menu) {
        try {
            //일반 회원 목룍
            if (menu == 1) {
                if (filter.equals("0")) {
                    //이름으로 검색
                    return RestResult.success(memberService.getMemberByName(keyword));
                } else {
                    return RestResult.success(memberService.getMemberByEmail(keyword));
                }
                // 호스트 목록
            } else {
                if (filter.equals("0")) {
                    return RestResult.success(memberService.getHostByName(keyword));
                } else {
                    // 이메일로 검색
                    return RestResult.success(memberService.getHostByEmail(keyword));
                }
            }
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    public RestResult<?> updateGrade(@RequestBody Member member) {
        try {
            return RestResult.success(memberService.updateGrade(member.getGradeNo(), member.getMemberNo()));
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }

    }
}
