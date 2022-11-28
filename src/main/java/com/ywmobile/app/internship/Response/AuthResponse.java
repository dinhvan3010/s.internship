package com.ywmobile.app.internship.Response;

import com.ywmobile.app.internship.enums.UserRole;
import lombok.Data;

@Data
public class AuthResponse {

    private String username;
    private String accessToken;


    private UserRole Role;

    public AuthResponse(String username, String accessToken, UserRole role) {
        this.username = username;
        this.accessToken = accessToken;
        Role = role;
    }

}
