package com.api.v1.doctors.exceptions;

public class TerminatedDoctorException extends RuntimeException {
    public TerminatedDoctorException() {
        super("Doctor is terminated.");
    }
}
