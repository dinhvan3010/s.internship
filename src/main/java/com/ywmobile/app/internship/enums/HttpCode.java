package com.ywmobile.app.internship.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpCode {
    SUCCESS(200, "SUCCESS"),
    CREATED(201, "CREATED"),
    CREATED_FAIL(203,"CREATED FAIL"),
    NO_CONTENT(204, "NO CONTENT"),
    EXIST_BY_USERNAME(205, "EXIST_BY_USERNAME"),
    EXIST_BY_DEPARTMENTNAME(206, "EXIST_BY_DEPARTMENT_NAME"),
    LOGIN_FAIL(207, "LOGIN FAIL"),
    DELETE_FAIL(208, "DELETE FAIL"),
    NOT_FOUND(404, "RECORD NOT FOUND"),
    VALIDATION_FAIL(501 , "VALIDATION FAIL"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    WRONG_FORMAT(405, "WRONG_FORMAT");

    private int code;
    private String msg;
}
