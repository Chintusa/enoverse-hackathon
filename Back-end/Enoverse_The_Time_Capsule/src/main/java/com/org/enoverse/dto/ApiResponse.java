package com.org.enoverse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String message;
	private String name;
	private LocalDateTime dateTime;
	private boolean status;

	/**
	 * @param message
	 * @param dateTime
	 * @param status
	 */
	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.dateTime = LocalDateTime.now();
		this.status = status;
	}

	public ApiResponse(String userName, String message, boolean status) {
		super();
		this.name = userName;
		this.message = message;
		this.dateTime = LocalDateTime.now();
		this.status = status;
	}

}
