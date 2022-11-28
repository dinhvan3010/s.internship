package com.ywmobile.app.internship.DTO;

import com.ywmobile.app.internship.Response.AuthResponse;
import com.ywmobile.app.internship.enums.HttpCode;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageResponse<T> {
    private int code;
    private String message;
    private T payload;


    public PageResponse(int code, String message, T payload) {
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public PageResponse(int code, String message ) {
        this.code = code;
        this.message = message;
    }
}


