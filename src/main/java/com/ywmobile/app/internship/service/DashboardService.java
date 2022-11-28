package com.ywmobile.app.internship.service;

import com.ywmobile.app.internship.DAO.DashboardDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.Dashboard;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.enums.UserRole;
import com.ywmobile.app.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    StaffDAO staffDAO;

    @Autowired
    DashboardDAO dashboardDAO;

    public Dashboard getStatistic() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dashboard dashboard;
        if (user.getRole().equals(UserRole.ADMIN)) {
            dashboard = dashboardDAO.dashboardAdmin();
        } else {
            StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
            dashboard = dashboardDAO.dashboardStaff(staffDetailDTO.getId());
        }
        return dashboard;
    }
}
