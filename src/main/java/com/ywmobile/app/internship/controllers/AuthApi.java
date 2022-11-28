package com.ywmobile.app.internship.controllers;

import com.ywmobile.app.internship.DTO.PageResponse;
import com.ywmobile.app.internship.Request.AuthRequest;
import com.ywmobile.app.internship.Response.AuthResponse;
import com.ywmobile.app.internship.enums.HttpCode;
import com.ywmobile.app.internship.jwt.JwtTokenUtil;
import com.ywmobile.app.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthApi {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping("/auth/login")
    public PageResponse<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            System.out.println(user);
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken, user.getRole());
            return new PageResponse<>(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg(), response);
        } catch (Exception e) {
            return new PageResponse<>(HttpCode.LOGIN_FAIL.getCode(), HttpCode.LOGIN_FAIL.getMsg());
        }

    }
}
