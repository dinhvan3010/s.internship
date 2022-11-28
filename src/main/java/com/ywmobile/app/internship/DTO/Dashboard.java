package com.ywmobile.app.internship.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Dashboard {
    int staffs;
    int leaveRequests;
    int departments;
}
