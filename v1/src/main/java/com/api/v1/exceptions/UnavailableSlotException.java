package com.api.v1.exceptions;

import java.time.LocalDateTime;

public class UnavailableSlotException extends RuntimeException {
    public UnavailableSlotException(LocalDateTime dateTime, String medicalLicenseNumber) {
        super("The slot which datetime is %s and doctor's license number is %s already occupied.".formatted(dateTime, medicalLicenseNumber));
    }
}
