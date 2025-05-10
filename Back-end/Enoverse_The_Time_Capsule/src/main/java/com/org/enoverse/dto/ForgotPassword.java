package com.org.enoverse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPassword {
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Old Password is required")
	private String oldPassword;
	@NotBlank(message = "New Password is required")
	@Size(min = 8, max = 16, message = "Password must be minimum of 8 characters and maximum of 16 characters!!")
//	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", message = "Password NotMatching!")
	private String newPassword;
}
