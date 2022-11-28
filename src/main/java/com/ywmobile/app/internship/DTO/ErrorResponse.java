package com.ywmobile.app.internship.DTO;


import java.util.List;

public class ErrorResponse {

    private int code;
    private String message;
    private List<ErrorDetail> errors;

    public ErrorResponse(int code, String message, List<ErrorDetail> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }
}