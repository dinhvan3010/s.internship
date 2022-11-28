package com.ywmobile.app.internship.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywmobile.app.internship.Request.StaffRequestPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Convert {
    @Autowired
    FormatDate formatDate;
    public StaffRequestPart getJson(String staff) {

        StaffRequestPart staffRequestPartJson = new StaffRequestPart();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            staffRequestPartJson = objectMapper.readValue(staff, StaffRequestPart.class);
            System.out.println(staffRequestPartJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffRequestPartJson;
    }
}
