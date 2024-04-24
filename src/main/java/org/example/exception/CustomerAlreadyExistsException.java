package org.example.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

	public CustomerAlreadyExistsException(String mobileNumber) {
		super("Customer already exists with mobile number " + mobileNumber);
	}
}