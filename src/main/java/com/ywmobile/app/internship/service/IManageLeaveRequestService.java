package com.ywmobile.app.internship.service;

import com.ywmobile.app.internship.DTO.LeaveDTO;
import com.ywmobile.app.internship.model.LeaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IManageLeaveRequestService {


    int getLeaveByKeyword(String keyword);

    List<LeaveRequest> listLeavesOfStaff();

    void createNewLeaveRequest(LeaveRequest leaveRequest);

    void updateStatusLeave(LeaveRequest leaveRequest);

    LeaveRequest findById(int id);
}
