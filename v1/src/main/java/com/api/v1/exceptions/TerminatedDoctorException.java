package com.api.v1.exceptions;

public class TerminatedDoctorException extends RuntimeException {
    public TerminatedDoctorException() {
        super("Doctor is terminated.");
    }
}
