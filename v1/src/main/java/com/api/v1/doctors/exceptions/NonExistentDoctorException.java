package com.api.v1.doctors.exceptions;

public class NonExistentDoctorException extends RuntimeException {
    public NonExistentDoctorException() {
        super("Doctor was not found.");
    }
}
