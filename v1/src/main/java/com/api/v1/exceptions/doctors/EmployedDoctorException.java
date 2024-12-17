package com.api.v1.exceptions.doctors;

public class EmployedDoctorException extends RuntimeException {
    public EmployedDoctorException() {
        super("Doctor is still employed.");
    }
}
