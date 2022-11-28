package com.ywmobile.app.internship.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorDetail {
    private String field;
    private String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
