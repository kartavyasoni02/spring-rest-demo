package com.rest.exception;

public class UserAlreadyExistException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String errorCode) {
		super(errorCode);
	}
}
