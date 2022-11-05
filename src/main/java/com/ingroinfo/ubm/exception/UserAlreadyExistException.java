package com.ingroinfo.ubm.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(final String msg) {
		super(msg);
	}

}