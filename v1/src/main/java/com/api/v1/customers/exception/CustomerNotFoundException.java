package com.api.v1.customers.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Customer was not found.");
    }
}
