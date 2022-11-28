package com.ywmobile.app.internship.service;

import com.ywmobile.app.internship.DTO.StaffDTO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.Request.StaffRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IManageUserService {

    boolean getExistByUsername( String username);


}
