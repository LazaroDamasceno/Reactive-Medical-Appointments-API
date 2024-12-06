package com.api.v1.exceptions.medical_appointments;

public class ImmutableMedicalAppointmentException extends RuntimeException {
    public ImmutableMedicalAppointmentException(String message) {
        super(message);
    }
}
