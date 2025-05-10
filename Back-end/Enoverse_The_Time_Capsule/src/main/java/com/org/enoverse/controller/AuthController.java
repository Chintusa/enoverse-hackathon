package com.org.enoverse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.enoverse.dto.ApiResponse;
import com.org.enoverse.dto.ForgotPassword;
import com.org.enoverse.dto.LoginRequest;
import com.org.enoverse.dto.UserDTO;
import com.org.enoverse.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enoverse/auth")
public class AuthController {
	private final UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

//	@RequestMapping("favicon.ico")
//	public ResponseEntity<ApiResponse> favicon() {
//		return ResponseEntity.ok();
//	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDTO userDto) {
		ApiResponse response = this.userService.register(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody LoginRequest request) {
		ApiResponse response = this.userService.loginUser(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/allusers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {

		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {
		UserDTO updatedUser = this.userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updatedUser);
	}

	@PutMapping("/update-password")
	public ResponseEntity<ApiResponse> updatePassword(@RequestBody ForgotPassword forgotPassword) {
		ApiResponse update_User_Password = this.userService.updatePassword(forgotPassword);
		return ResponseEntity.ok(update_User_Password);
	}
}
