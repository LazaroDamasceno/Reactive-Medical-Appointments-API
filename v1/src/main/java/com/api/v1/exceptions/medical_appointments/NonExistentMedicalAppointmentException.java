package com.api.v1.exceptions.medical_appointments;

public class NonExistentMedicalAppointmentException extends RuntimeException {
    public NonExistentMedicalAppointmentException() {
        super("Medical appointment was not found.");
    }
}
