package com.api.v1.doctors.exceptions;

public class DuplicatedDoctorLicenseNumberException extends RuntimeException {
    public DuplicatedDoctorLicenseNumberException() {
        super("The given doctor's license number is already in use.");
    }
}
