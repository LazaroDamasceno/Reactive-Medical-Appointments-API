package com.api.v1.doctors.exceptions;

public class DuplicatedDoctorLicenseNumberException extends RuntimeException {
    public DuplicatedDoctorLicenseNumberException() {
        super("Doctor was not found.");
    }
}
