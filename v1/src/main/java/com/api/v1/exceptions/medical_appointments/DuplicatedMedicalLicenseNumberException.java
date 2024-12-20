package com.api.v1.exceptions.medical_appointments;

public class DuplicatedMedicalLicenseNumberException extends RuntimeException {
    public DuplicatedMedicalLicenseNumberException() {
        super("The given doctor's license number is already in use.");
    }
}
