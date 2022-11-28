package com.ywmobile.app.internship.controllers;


import com.ywmobile.app.internship.DTO.Dashboard;
import com.ywmobile.app.internship.DTO.PageResponse;
import com.ywmobile.app.internship.enums.HttpCode;
import com.ywmobile.app.internship.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/api/dashboard")
    public PageResponse<Dashboard> listStaff() {
        Dashboard dashboard = dashboardService.getStatistic();
        return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), dashboard);
    }
}
