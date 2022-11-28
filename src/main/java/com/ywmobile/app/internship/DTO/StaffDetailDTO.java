package com.ywmobile.app.internship.DTO;

import com.ywmobile.app.internship.enums.GenderEnum;
import com.ywmobile.app.internship.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StaffDetailDTO {
    private int id ;
    private String username;
    private String staff_name;
    private  String department_name;
    private GenderEnum gender;
    private  String email;
    private String mobile;
    private String photo;
    private LocalDate date_of_birth;
    private Date join_on;
    private String address;
    private String city;
    private String state;
    private String country;

}
