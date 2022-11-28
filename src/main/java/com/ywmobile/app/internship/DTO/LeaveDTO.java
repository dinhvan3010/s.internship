package com.ywmobile.app.internship.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LeaveDTO {
    private int id;
    private String staff_name;
    private String photo;
    private String department_name;
    private String reason;
    private Date leave_from;
    private Date leave_to;
    private String description;
    private String status;
    private Date applied_on;
}
