package com.api.v1.exceptions;

public class ImmutableMedicalAppointmentException extends RuntimeException {
    public ImmutableMedicalAppointmentException(String message) {
        super(message);
    }
}
