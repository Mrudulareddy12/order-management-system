package org.example.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(Long id) {
		super("Could not find User id " + id);
	}
}