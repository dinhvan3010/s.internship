package com.ywmobile.app.internship.Validator;

import com.ywmobile.app.internship.DTO.ErrorDetail;
import com.ywmobile.app.internship.DTO.ErrorResponse;
import com.ywmobile.app.internship.enums.HttpCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler()
//    void handleMultipartException(MultipartException ex, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value(), "Please select a file");
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDetail> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ErrorDetail errorDetail = new ErrorDetail(error.getField(), error.getDefaultMessage());
            errors.add(errorDetail);
        }
        ErrorResponse response = new ErrorResponse(HttpCode.VALIDATION_FAIL.getCode(), HttpCode.VALIDATION_FAIL.getMsg(), errors);
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }
}