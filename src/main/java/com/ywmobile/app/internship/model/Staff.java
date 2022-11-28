package com.ywmobile.app.internship.model;


import com.ywmobile.app.internship.enums.GenderEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class Staff {
    private int id;
    private int department_id;
    private int user_id;
    private String staff_name;
    private String photo;
    private GenderEnum gender;
    private String mobile;
    private String email;
    private Date date_of_birth;
    private Date join_on;
    private String address;
    private String city;
    private String state;
    private String country;

}
