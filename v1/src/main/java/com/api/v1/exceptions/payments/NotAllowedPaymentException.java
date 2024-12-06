package com.api.v1.exceptions.payments;

public class NotAllowedPaymentException extends RuntimeException {
    public NotAllowedPaymentException() {
        super("The medical appointment is not completed yet.");
    }
}
