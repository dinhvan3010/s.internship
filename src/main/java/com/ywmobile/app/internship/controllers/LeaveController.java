package com.ywmobile.app.internship.controllers;

import com.ywmobile.app.internship.DAO.LeaveRequestDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.*;
import com.ywmobile.app.internship.enums.HttpCode;
import com.ywmobile.app.internship.model.LeaveRequest;
import com.ywmobile.app.internship.model.User;
import com.ywmobile.app.internship.service.IManageLeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LeaveController {
    @Autowired
    IManageLeaveRequestService manageLeaveService;

    @Autowired
    LeaveRequestDAO leaveRequestDAO;

    @Autowired
    StaffDAO staffDAO;

    @GetMapping("/admin/leave/list")
    public PageResponse<PagingResponse<List<LeaveDTO>>> listLeavesOfAdmin(@RequestParam(value = "pageNo", required = false) int pageNo,
                                                                          @RequestParam(value = "pageSize", required = false) int pageSize,
                                                                          @RequestParam(required = false) String keyword) {

        int totalItems = manageLeaveService.getLeaveByKeyword(keyword);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        Paging paging = new Paging(pageSize * (pageNo - 1), pageSize, keyword);
        List<LeaveDTO> LeaveDTOPaging = leaveRequestDAO.getLeaveRequestPaging(paging);
        PagingResponse<List<LeaveDTO>> listPagingResponse = new PagingResponse<>(LeaveDTOPaging, totalItems, totalPages, pageNo);
        try {
            if (totalItems == 0) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg(), listPagingResponse);
            }
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), listPagingResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    @GetMapping("/leave/list")
    public PageResponse<PagingResponse<List<LeaveRequest>>> listLeavesOfStaff(@RequestParam(value = "pageNo", required = false) int pageNo,
                                                                              @RequestParam(value = "pageSize", required = false) int pageSize,
                                                                              @RequestParam(required = false) String keyword) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
        Paging count = new Paging(keyword, staffDetailDTO.getId());
        int totalItems = leaveRequestDAO.getTotalItems(count);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        Paging paging = new Paging(pageSize * (pageNo - 1), pageSize, keyword, staffDetailDTO.getId());
        List<LeaveRequest> leaves = leaveRequestDAO.getLeaveForStaff(paging);
        PagingResponse<List<LeaveRequest>> listPagingResponse = new PagingResponse<>(leaves, totalItems, totalPages, pageNo);
        try {
            if (leaves.isEmpty()) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg(), listPagingResponse);
            }
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), listPagingResponse);
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    @GetMapping("/admin/leave/request")
    public PageResponse<PagingResponse<List<LeaveDTO>>> getListLeaveRequest(@RequestParam(value = "pageNo", required = false) int pageNo,
                                                                            @RequestParam(value = "pageSize", required = false) int pageSize,
                                                                            @RequestParam(required = false) String keyword) {

        try {
            int totalItems = leaveRequestDAO.getCountListLeaveRequest(keyword);
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);
            Paging paging = new Paging(pageSize * (pageNo - 1), pageSize, keyword);
            List<LeaveDTO> leavesPaging = leaveRequestDAO.getListLeaveRequestAdmin(paging);
            PagingResponse<List<LeaveDTO>> listPagingResponse = new PagingResponse<>(leavesPaging, totalItems, totalPages, pageNo);
            if (totalItems == 0) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg(), listPagingResponse);
            }
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), listPagingResponse);
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    @PostMapping("/leave/create")
    public PageResponse<LeaveRequest> createNewLeaveRequest(@Valid @RequestBody LeaveRequest leaveRequest) {
        try {
            if (leaveRequest.getLeave_from().compareTo(leaveRequest.getLeave_to()) < 0) {
                manageLeaveService.createNewLeaveRequest(leaveRequest);
                return new PageResponse<>(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg());
            } else {
                return new PageResponse<>(HttpCode.VALIDATION_FAIL.getCode(), HttpCode.VALIDATION_FAIL.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    @PutMapping("admin/leave/update")
    public PageResponse<LeaveRequest> updateStatusLeave( @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest LeaveData = manageLeaveService.findById(leaveRequest.getId());
        try {
            if (LeaveData == null) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
            } else {
                manageLeaveService.updateStatusLeave(leaveRequest);
                return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg());
            }
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

}
