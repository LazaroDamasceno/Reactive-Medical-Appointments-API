package com.api.v1.payment.exceptions;

public class NotAllowedPaymentException extends RuntimeException {
    public NotAllowedPaymentException() {
        super("The medical appointment is not completed yet.");
    }
}
