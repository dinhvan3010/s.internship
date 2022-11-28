package com.ywmobile.app.internship.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ywmobile.app.internship.enums.GenderEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffRequestPart {
    private int id;
    @Length(min = 5, max = 20, message = "Username must be between 5 and 20")
    private String username;
    @Length(min = 5, max = 20, message = "Password must be between 5 and 20")
    private String password;
    @NotBlank
    private String staff_name;
    private int department_id;
    private GenderEnum gender;
    @Email
    private String email;
    @NotBlank
    private String mobile;
    private String photo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date_of_birth;
    private Date join_on;
    private String address;
    private String city;
    private String state;
    private String country;
    private boolean enabled;

}
