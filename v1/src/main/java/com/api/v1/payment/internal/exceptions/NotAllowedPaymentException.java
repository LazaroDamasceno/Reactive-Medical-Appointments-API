package com.api.v1.payment.internal.exceptions;

public class NotAllowedPaymentException extends RuntimeException {
    public NotAllowedPaymentException() {
        super("The medical appointment is not completed yet.");
    }
}
