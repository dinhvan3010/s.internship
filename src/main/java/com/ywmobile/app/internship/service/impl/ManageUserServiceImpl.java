package com.ywmobile.app.internship.service.impl;

import com.ywmobile.app.internship.DAO.DepartmentDAO;
import com.ywmobile.app.internship.DAO.StaffDAO;
import com.ywmobile.app.internship.DAO.UserDAO;
import com.ywmobile.app.internship.DTO.StaffDTO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.Request.StaffRequest;
import com.ywmobile.app.internship.service.IManageStaffService;
import com.ywmobile.app.internship.service.IManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManageUserServiceImpl implements IManageUserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public boolean getExistByUsername(String username) {
        return userDAO.getExistByUsername(username);
    }
}
