package com.api.v1.exceptions.payments;

public class NonExistentPaymentException extends RuntimeException {
    public NonExistentPaymentException() {
        super("The payment was not found.");
    }
}
