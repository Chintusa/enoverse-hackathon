package com.org.enoverse.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	private String resource;
	private String fieldName;

	private String email_value;
	private Integer value;

	/**
	 * @param resource
	 * @param fieldName
	 * @param value
	 */
	public ResourceNotFoundException(String resource, String fieldName, String email_value) {
		super(String.format("%s not found with %s : %s", resource, fieldName, email_value));
		this.resource = resource;
		this.fieldName = fieldName;
		this.email_value = email_value;
	}

	public ResourceNotFoundException(String resource, String fieldName, Integer value) {
		super(String.format("%s not found with %s : %d", resource, fieldName, value));
		this.resource = resource;
		this.fieldName = fieldName;
		this.value = value;
	}

}
