package com.api.v1.exceptions.medical_appointments;

public class InvalidMedicalAppointmentBookingDateTimeException extends RuntimeException {
    public InvalidMedicalAppointmentBookingDateTimeException() {
        super("The given booking datetime must, at least, be booked tomorrow.");
    }
}
