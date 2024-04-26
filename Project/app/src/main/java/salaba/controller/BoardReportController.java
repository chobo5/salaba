package salaba.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import salaba.service.BoardReportService;
import salaba.service.BoardService;
import salaba.service.StorageService;
import salaba.util.Translator;
import salaba.vo.board.Board;
import salaba.vo.board.BoardFile;
import salaba.vo.board.BoardReport;
import salaba.vo.Member;
import salaba.vo.board.BoardReportFile;

@RequiredArgsConstructor
@Controller
@SessionAttributes("boardReportFiles")
public class BoardReportController {

  private static final Log log = LogFactory.getLog(BoardController.class);
  private final BoardReportService boardReportService; // 신고 서비스
  private final StorageService storageService; // 스토리지 서비스

  private String uploadDir = "board/";

  @Value("${ncp.bucketname}")
  private String bucketName;

  @PostMapping("/board/report/add") // 신고 작성
  public String addReport(
      BoardReport boardReport,
      @RequestParam("targetNo") int targetNo,
      @RequestParam("targetType") String targetType,
      MultipartFile[] reportFiles,
      HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하시기 바랍니다!");
    }
    log.debug("-----------" + reportFiles);

    // 필요한 값
    boardReport.setWriter(loginUser);
    boardReport.setCategoryNo(boardReport.getCategoryNo());
    boardReport.setTargetNo(targetNo);
    boardReport.setTargetType(targetType);

    List<BoardReportFile> reportFileList = new ArrayList<>();
    if (reportFiles != null && reportFiles.length > 0) {
      for (MultipartFile file: reportFiles) {
        if (file.getSize() == 0) {
          throw new Exception("첨부파일을 등록하세요!");
        }
        String filename = storageService.upload(this.bucketName, this.uploadDir, file);
        reportFileList.add(BoardReportFile.builder().uuidFileName(filename).oriFileName(file.getName()).build());
      }
    }

    if (reportFileList.size() > 0) {
      boardReport.setReportFileList(reportFileList);
    }

    boardReportService.addReport(boardReport);

    return "redirect:/board/list?categoryNo=" + boardReport.getCategoryNo();
  }

  @GetMapping("/board/report/form")
  public void report(
      @RequestParam("targetNo") int targetNo,
      @RequestParam("targetType") String targetType,
      Model model)
      throws Exception { // 신고 폼
    System.out.println("----------------------------");
    System.out.println(targetNo);
    System.out.println(targetType);
    model.addAttribute("targetNo", targetNo);
    model.addAttribute("targetType", targetType);
  }
}
