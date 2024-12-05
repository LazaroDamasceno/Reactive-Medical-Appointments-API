package com.api.v1.medical_appointments.exceptions;

public class ImmutableMedicalAppointmentException extends RuntimeException {
    public ImmutableMedicalAppointmentException(String message) {
        super(message);
    }
}
