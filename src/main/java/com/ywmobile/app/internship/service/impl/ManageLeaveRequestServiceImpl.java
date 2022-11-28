package com.ywmobile.app.internship.service.impl;

import com.ywmobile.app.internship.DAO.LeaveRequestDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.model.LeaveRequest;
import com.ywmobile.app.internship.enums.LeaveStatus;
import com.ywmobile.app.internship.model.User;
import com.ywmobile.app.internship.service.IManageLeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManageLeaveRequestServiceImpl implements IManageLeaveRequestService {
    @Autowired
    LeaveRequestDAO leaveRequestDAO;

    @Autowired
    StaffDAO staffDAO;


    @Override
    public int getLeaveByKeyword(String keyword) {
     int totals = leaveRequestDAO.getCountLeave(keyword);
        return totals;
    }

    @Override
    public List<LeaveRequest> listLeavesOfStaff() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
//        List<LeaveRequest> Leaves = leaveRequestDAO.getLeaveForStaff(staffDetailDTO.getId());
//        return Leaves;
        return null;
    }

    @Override
    public void createNewLeaveRequest(LeaveRequest leaveRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
        leaveRequest.setStaff_id(staffDetailDTO.getId());
        Date date = new Date(System.currentTimeMillis());
        leaveRequest.setApplied_on(date);
        leaveRequest.setStatus(LeaveStatus.PENDING);
        System.out.println(leaveRequest);
        leaveRequestDAO.save(leaveRequest);
    }

    @Override
    public void updateStatusLeave(LeaveRequest leaveRequest) {
        leaveRequestDAO.update(leaveRequest);
    }

    @Override
    public LeaveRequest findById(int id) {
        LeaveRequest leaveRequest = leaveRequestDAO.findById(id);
        return leaveRequest;
    }
}
