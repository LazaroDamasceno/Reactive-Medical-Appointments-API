package com.api.v1.medical_appointment.exceptions;

public class UnavailableBookingDateException extends RuntimeException {
    public UnavailableBookingDateException(String message) {
        super(message);
    }
}
