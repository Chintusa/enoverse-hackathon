package com.org.enoverse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.enoverse.dto.ApiResponse;
import com.org.enoverse.dto.ForgotPassword;
import com.org.enoverse.dto.LoginRequest;
import com.org.enoverse.dto.UserDTO;
import com.org.enoverse.exceptions.ResourceNotFoundException;
import com.org.enoverse.model.User;
import com.org.enoverse.repository.UserRepository;
import com.org.enoverse.service.UserService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ModelMapper mapper;

	@Autowired
	public UserServiceImpl(ModelMapper mapper, UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public User convertToUser(UserDTO dto) {

		return this.mapper.map(dto, User.class);
	}

	public UserDTO convertToUserDto(User user) {

		return this.mapper.map(user, UserDTO.class);
	}

	@Override
	public ApiResponse register(UserDTO userDto) {
		if (this.repository.findByEmail(userDto.getEmail()).isPresent()) {
			return new ApiResponse("Email is already registered!", false);
		}
		User user = this.convertToUser(userDto);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		this.convertToUserDto(repository.save(user));
		return new ApiResponse("User Registered Successfully!", true);
	}

	@Override
	public ApiResponse loginUser(LoginRequest loginRequest) {
		User user = this.repository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", loginRequest.getEmail()));
		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("Invalid Email or Password.");
		}

		return new ApiResponse(user.getName(), "Login Successfull!", true);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.convertToUserDto(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		if ((userDto.getPassword() != null) && (!userDto.getPassword().isEmpty())
				&& (!passwordEncoder.matches(userDto.getPassword(), user.getPassword()))) {

			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		return this.convertToUserDto(this.repository.save(user));

	}

	@Override
	public ApiResponse updatePassword(ForgotPassword updatePassword) {
		User user = this.repository.findByEmail(updatePassword.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", updatePassword.getEmail()));
		if (!passwordEncoder.matches(updatePassword.getOldPassword(), user.getPassword())) {
			return new ApiResponse("Old password is incorrect", false);
		}
		user.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
		this.repository.save(user);

		return new ApiResponse("Password updated successfully", true);
	}

	public List<UserDTO> getAllUsers() {
		return this.repository.findAll().stream().map(this::convertToUserDto).collect(Collectors.toList());

	}

}
