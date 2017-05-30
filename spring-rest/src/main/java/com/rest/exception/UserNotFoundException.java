package com.rest.exception;

public class UserNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String errorCode) {
		super(errorCode);
	}

}
