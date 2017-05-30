package com.rest.dto;

public class MessageDTO {
	private String message;
	private String errorCode;

	public MessageDTO() {
		super();
	}

	public MessageDTO(String message) {
		super();
		this.message = message;
	}

	public MessageDTO(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
