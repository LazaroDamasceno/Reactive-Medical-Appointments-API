package com.api.v1.exceptions;

public class NonExistentMedicalAppointmentException extends RuntimeException {
    public NonExistentMedicalAppointmentException() {
        super("Medical appointment was not found.");
    }
}
