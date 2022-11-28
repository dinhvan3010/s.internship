package com.ywmobile.app.internship.DTO;

import com.ywmobile.app.internship.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StaffDTO {
    private int id;
    private String name;
    private String photo;
    private String departmentName;
    private GenderEnum gender;
    private String mobile;
    private Date join_On;
}
