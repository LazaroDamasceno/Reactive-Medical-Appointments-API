package com.api.v1.doctors.internal.exceptions;

public class TerminatedDoctorException extends RuntimeException {
    public TerminatedDoctorException() {
        super("Doctor is terminated.");
    }
}
