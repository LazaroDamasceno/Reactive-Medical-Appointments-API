package com.api.v1.medical_appointment.exceptions;

public class ImmutableMedicalAppointmentException extends RuntimeException {
    public ImmutableMedicalAppointmentException(String message) {
        super("The sought medical appointment cannot be changed.");
    }
}
