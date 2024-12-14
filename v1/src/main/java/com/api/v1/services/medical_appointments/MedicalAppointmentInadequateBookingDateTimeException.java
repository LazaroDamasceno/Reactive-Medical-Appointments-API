package com.api.v1.services.medical_appointments;

public class MedicalAppointmentInadequateBookingDateTimeException extends RuntimeException {
    public MedicalAppointmentInadequateBookingDateTimeException() {
        super("The given booking datetime must, at least, be booked tomorrow.");
    }
}
