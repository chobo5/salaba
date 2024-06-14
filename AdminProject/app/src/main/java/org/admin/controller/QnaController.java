package org.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admin.domain.Qna;
import org.admin.service.QnaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
@Slf4j
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/list")
    public RestResult<?> qnaList() {
        try {
            return RestResult.success(qnaService.getAllQna());
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/view/{qnaNo}")
    public RestResult<?> qnaView(@PathVariable int qnaNo) {
        try {
            return RestResult.success(qnaService.getBy(qnaNo));
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public RestResult<?> addAnswer(@RequestBody Qna qna) {
        try {
            qnaService.addAnswer(qna);
            return RestResult.success();
        } catch (Exception e) {
            return RestResult.error(e.getMessage());
        }
    }
}
