package org.example.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	public ProductAlreadyExistsException(String name) {
		super("Product already exists with name" + name);
	}
}