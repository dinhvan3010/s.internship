package com.ywmobile.app.internship.service;

import com.ywmobile.app.internship.DTO.StaffDTO;
import com.ywmobile.app.internship.DTO.StaffDetailDTO;
import com.ywmobile.app.internship.Request.StaffRequest;
import com.ywmobile.app.internship.Request.StaffRequestPart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IManageStaffService {

    List<StaffDTO> getAllStaff();

    public int getTotalItem(String keyword);

    void save(StaffRequestPart staffRequestPart ) ;


    void delete(Integer id);

    StaffDetailDTO findById(int id);

    void update(StaffRequestPart staffRequestPart);


}
