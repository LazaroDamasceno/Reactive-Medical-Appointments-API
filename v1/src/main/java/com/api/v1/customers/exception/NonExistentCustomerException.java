package com.api.v1.customers.exception;

public class NonExistentCustomerException extends RuntimeException {
    public NonExistentCustomerException() {
        super("Customer was not found.");
    }
}
