package org.example.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(Long id) {
		super("Could not find order id " + id);
	}
}