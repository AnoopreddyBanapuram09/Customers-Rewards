package com.customers.rewards.exception;

import org.springframework.http.HttpStatus;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
public class CustomerRewardsException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;

	public CustomerRewardsException() {
		super();
	}

	public CustomerRewardsException(HttpStatus code, String message) {
		super(message);
		this.statusCode = code;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
