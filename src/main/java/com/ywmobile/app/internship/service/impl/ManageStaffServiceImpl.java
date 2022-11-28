package com.ywmobile.app.internship.service.impl;

import com.ywmobile.app.internship.DAO.DepartmentDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DTO.StaffDTO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.Request.StaffRequestPart;
import com.ywmobile.app.internship.service.CloudinaryService;
import com.ywmobile.app.internship.service.IManageStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManageStaffServiceImpl implements IManageStaffService {
    @Autowired
    StaffDAO staffDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    CloudinaryService cloudinaryService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffDTO> staffs = staffDAO.getAllStaff();
        return staffs;
    }

    @Override
    public int getTotalItem(String keyword) {
        int totalItems = staffDAO.getTotalStaffByKeyword(keyword);
        return totalItems;
    }

    @Override
    public void save(StaffRequestPart StaffRequestPart) {
        StaffRequestPart.isEnabled();
        Date date = new Date(System.currentTimeMillis());
        StaffRequestPart.setJoin_on(date);
        StaffRequestPart.setPassword(passwordEncoder.encode(StaffRequestPart.getPassword()));
        staffDAO.save(StaffRequestPart);

    }

    @Override
    public void delete(Integer id) {
        staffDAO.delete(id);
    }

    @Override
    public StaffDetailDTO findById(int id) {
        StaffDetailDTO staffDetailDTO = staffDAO.findById(id);
        return staffDetailDTO;
    }

    @Override
    public void update(StaffRequestPart staffRequestPart) {
        staffRequestPart.setPassword(passwordEncoder.encode(staffRequestPart.getPassword()));
        staffDAO.update(staffRequestPart);
    }
}
