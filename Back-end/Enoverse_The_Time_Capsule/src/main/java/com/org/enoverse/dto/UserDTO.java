package com.org.enoverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Integer id;

	@Size(min = 4, message = "Username must be minimum of 4 characters!!")
	@NotBlank(message = "Name Cannot be Empty")
	private String name;

	@Email(message = "Invalid Email address!")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 16, message = "Password must be minimum of 8 characters and maximum of 16 characters!!")
//	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", message = "Invalid Password!")
	private String password;

}
