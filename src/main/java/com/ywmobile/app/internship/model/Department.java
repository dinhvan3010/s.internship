package com.ywmobile.app.internship.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Department  {
    private int id;
    @NotEmpty(message = "Please provide a name department")
    private String name;
    private  boolean primaryKey;
}
