package com.api.v1.exceptions.doctors;

public class NonExistentDoctorException extends RuntimeException {
    public NonExistentDoctorException() {
        super("Doctor was not found.");
    }
}
