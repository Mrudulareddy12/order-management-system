package org.example.exception;

public class StockNotAvailableException extends RuntimeException {

	public StockNotAvailableException(String productName) {
		super("Stock is not available for this product "+productName);
	}
}