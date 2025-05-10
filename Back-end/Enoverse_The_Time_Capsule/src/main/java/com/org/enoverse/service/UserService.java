package com.org.enoverse.service;

import java.util.List;

import com.org.enoverse.dto.ApiResponse;
import com.org.enoverse.dto.ForgotPassword;
import com.org.enoverse.dto.LoginRequest;
import com.org.enoverse.dto.UserDTO;

public interface UserService {
	ApiResponse register(UserDTO userDto);

	ApiResponse loginUser(LoginRequest loginRequest);

	UserDTO getUserById(Integer userId);

	UserDTO updateUser(UserDTO user, Integer userId);

	List<UserDTO> getAllUsers();

	ApiResponse updatePassword(ForgotPassword updatePassword);
}