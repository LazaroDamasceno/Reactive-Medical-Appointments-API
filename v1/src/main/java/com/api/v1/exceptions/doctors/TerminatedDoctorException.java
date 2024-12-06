package com.api.v1.exceptions.doctors;

public class TerminatedDoctorException extends RuntimeException {
    public TerminatedDoctorException() {
        super("Doctor is terminated.");
    }
}
