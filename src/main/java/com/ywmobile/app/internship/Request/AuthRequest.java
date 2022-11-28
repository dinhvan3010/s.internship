package com.ywmobile.app.internship.Request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;



@Data
public class AuthRequest {
	@Length(min = 5, max = 20, message = "Username must be between 5 and 20")
	private String username;
	
	@Length(min = 5, max = 20, message = "Password must be between 5 and 20")
	private String password;
	
}
