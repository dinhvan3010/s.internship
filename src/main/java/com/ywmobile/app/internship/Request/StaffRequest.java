package com.ywmobile.app.internship.Request;

import com.ywmobile.app.internship.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Optional;

@Data
@Getter
@Setter
@AllArgsConstructor
public class StaffRequest {
    private int id;
    private String username;
    private String password;
    private String staff_name;
    private int department_id;
    private GenderEnum gender;
    private String email;
    private String mobile;
    private String photo;
    private Date date_of_birth;
    private Date join_on;
    private String address;
    private String city;
    private String state;
    private String country;
    private boolean enabled;


    public StaffRequest(String username, String password, String staff_name, int department_id, GenderEnum gender, String email, String mobile, String photo, Date date_of_birth, Date join_on, String address, String city, String state, String country) {
        super();
        this.username = username;
        this.password = password;
        this.staff_name = staff_name;
        this.department_id = department_id;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.photo = photo;
        this.date_of_birth = date_of_birth;
        this.join_on = join_on;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }


    public StaffRequest(int id, String username, String password, String staff_name, int department_id, GenderEnum gender, String email, String mobile, String photo, Date date_of_birth, String address, String city, String state, String country) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.staff_name = staff_name;
        this.department_id = department_id;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.photo = photo;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
