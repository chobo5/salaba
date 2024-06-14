package org.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admin.service.ChartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/chart")
public class ChartController {

    private final ChartService chartService;
    @GetMapping("/boardCount")
    public RestResult<?> boardCountInMonth() {
        try {
            return RestResult.success(chartService.getBoardCountInMonth());
        } catch (RuntimeException e) {
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/joinCount")
    public RestResult<?> joinCountInMonth() {
        try {
            return RestResult.success(chartService.getJoinCountInMonth());
        } catch (RuntimeException e) {
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/gradeCount")
    public RestResult<?> userCountByGrade() {
        try {
            return RestResult.success(chartService.getUserCountByGrade());
        } catch (RuntimeException e) {
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/rentalCount")
    public RestResult<?> rentalCountByRegion() {
        try {
            return RestResult.success(chartService.getRentalCountByRegion());
        } catch (RuntimeException e) {
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/unprocessed")
    public RestResult<?> unprocessedWorks() {
        try {
            return RestResult.success(chartService.getUnprocessedWorks());
        } catch (RuntimeException e) {
            return RestResult.error(e.getMessage());
        }
    }
}
