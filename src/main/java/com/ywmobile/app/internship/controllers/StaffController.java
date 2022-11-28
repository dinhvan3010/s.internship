package com.ywmobile.app.internship.controllers;

import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.*;
import com.ywmobile.app.internship.Request.StaffRequestPart;
import com.ywmobile.app.internship.enums.HttpCode;
import com.ywmobile.app.internship.model.User;
import com.ywmobile.app.internship.service.CloudinaryService;
import com.ywmobile.app.internship.service.IManageStaffService;
import com.ywmobile.app.internship.service.IManageUserService;
import com.ywmobile.app.internship.utils.FormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import javax.validation.Validator;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;


@Validated
@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    IManageStaffService manageStaffService;

    @Autowired
    IManageUserService manageUserService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    FormatDate formatDate;

    @Autowired
    protected Validator validator;


    @RequestMapping(value = "/staff/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PageResponse<?> createNewStaff(@RequestPart("staff") @Valid StaffRequestPart staffRequestPart, @RequestPart MultipartFile file) {
        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if (bi == null) {
                return new PageResponse<>(HttpCode.WRONG_FORMAT.getCode(), HttpCode.WRONG_FORMAT.getMsg());
            }
            if (manageUserService.getExistByUsername(staffRequestPart.getUsername())) {
                Map result = cloudinaryService.upload(file);
                staffRequestPart.setPhoto((String) result.get("url"));
                manageStaffService.save(staffRequestPart);
                return new PageResponse<>(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg());
            }
            return new PageResponse<>(HttpCode.EXIST_BY_USERNAME.getCode(), HttpCode.EXIST_BY_USERNAME.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    @GetMapping("/staff/list")
    public PageResponse<PagingResponse<List<StaffDTO>>> listStaff(@RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "pageSize", required = false) int pageSize, @RequestParam(required = false) String keyword) {
        int totalItems = manageStaffService.getTotalItem(keyword);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        Paging paging = new Paging(pageSize * (pageNo - 1), pageSize, keyword);
        List<StaffDTO> departmentsPaging = staffDAO.getStaffPaging(paging);
        PagingResponse<List<StaffDTO>> listPagingResponse = new PagingResponse<>(departmentsPaging, totalItems, totalPages, pageNo);
        try {
            if (totalItems == 0) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg(), listPagingResponse);
            }
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), listPagingResponse);
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    @GetMapping("/staff/detail")
    public PageResponse<StaffDetailDTO> findStaff(@RequestParam(required = false) int id) {
        StaffDetailDTO staffDetailDTO = manageStaffService.findById(id);
        if (staffDetailDTO == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        } else {
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), staffDetailDTO);
        }
    }


    @DeleteMapping("/staff/delete")
    public PageResponse<HttpCode> deleteStaff(@RequestParam int id) {
        StaffDetailDTO staffDetailDTO = manageStaffService.findById(id);
        try {
            if (staffDetailDTO == null) {
                return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
            } else {
                manageStaffService.delete(id);
                return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg());
            }
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    @GetMapping("/user/profile")
    public PageResponse<StaffDetailDTO> profileStaff() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
        if (staffDetailDTO == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        }
        return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), staffDetailDTO);
    }


    @PutMapping(value = "/staff/update/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PageResponse<?> updateStaff(@PathVariable int id, @RequestPart("staff") @Valid StaffRequestPart staffRequestPart, MultipartFile file) {
        StaffDetailDTO staffDetailDTO = manageStaffService.findById(id);
        if (staffDetailDTO == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        } else {
            return update(staffRequestPart, file, id);
        }
    }

    @PutMapping(value = "/user/update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PageResponse<?> updateProfile(@RequestPart("staff") @Valid StaffRequestPart staffRequestPart, MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return new PageResponse<>(HttpCode.NO_CONTENT.getCode(), HttpCode.NO_CONTENT.getMsg());
        } else {
            StaffDetailDTO staffDetailDTO = staffDAO.getStaffByUserId(user.getId());
            return update(staffRequestPart, file, staffDetailDTO.getId());
        }
    }

    public PageResponse<?> update(StaffRequestPart staffRequestPart, MultipartFile file, int id) {
        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if (bi == null) {
                return new PageResponse<>(HttpCode.WRONG_FORMAT.getCode(), HttpCode.WRONG_FORMAT.getMsg());
            }
            if (manageUserService.getExistByUsername(staffRequestPart.getUsername())) {
                Map result = cloudinaryService.upload(file);
                staffRequestPart.setPhoto((String) result.get("url"));
                staffRequestPart.setId(id);
                manageStaffService.update(staffRequestPart);
                return new PageResponse<>(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg());
            }
            return new PageResponse<>(HttpCode.EXIST_BY_USERNAME.getCode(), HttpCode.EXIST_BY_USERNAME.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResponse<>(HttpCode.INTERNAL_SERVER_ERROR.getCode(), HttpCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }
}
