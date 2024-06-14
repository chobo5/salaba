package org.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.admin.domain.Member;
import org.admin.domain.Photo;
import org.admin.domain.Rental;
import org.admin.service.RentalService;
import org.admin.service.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
@Slf4j
public class RentalManageController {
    private final RentalService rentalService;

    @GetMapping("/list/{menu}")
    public RestResult<?> rentalList(@PathVariable int menu) {
        switch (menu) {
            case 1:
                return RestResult.success(rentalService.getAll());
            case 2:
                return RestResult.success(rentalService.getAppliedRentals());
            default:
                return RestResult.error("유효하지 않은 menu 입니다.");
        }
    }

    @GetMapping("/view/{menu}/{rentalNo}")
    public RestResult<?> rentalView(@PathVariable int menu,
                                    @PathVariable int rentalNo) {
        return RestResult.success(rentalService.getBy(rentalNo));
    }

    @PutMapping("/update")
    public RestResult<?> rentalUpdate(@RequestBody Rental rental) {
        rentalService.updateState(rental.getRentalNo(), rental.getState());
        return RestResult.success();
    }

    @GetMapping("/search/{keyword}/{filter}")
    public RestResult<?> searchRental(@PathVariable String keyword,
                                      @PathVariable String filter) {
        if (filter.equals("0")) {
            // 숙소명으로 검색
            return RestResult.success(rentalService.getAllByName(keyword));
        } else {
            // 호스트명로 검색
            return RestResult.success(rentalService.getAllByHostName(keyword));
        }
    }
}
