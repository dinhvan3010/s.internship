package com.ywmobile.app.internship.model;

import com.ywmobile.app.internship.enums.LeaveStatus;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class LeaveRequest {
    private int id;
    private int staff_id;
    @NotBlank(message = "Reason is mandatory.")
    private String reason;
    @Future(message = "leave from must be a date in the future")
    private Date leave_from;
    @Future(message = "leave to must be a date in the future")
    private Date leave_to;
    @NotBlank(message = "Description is mandatory.")
    private String description;
    private LeaveStatus status;
    private Date applied_on;
}
