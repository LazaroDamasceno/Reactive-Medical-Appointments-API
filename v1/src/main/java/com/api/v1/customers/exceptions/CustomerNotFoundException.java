package com.api.v1.customers.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String ssn) {
        super("Customer whose SSN is %s was not found.");
    }
}
