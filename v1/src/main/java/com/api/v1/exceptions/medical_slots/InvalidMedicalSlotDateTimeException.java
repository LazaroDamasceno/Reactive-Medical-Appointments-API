package com.api.v1.exceptions.medical_slots;

public class InvalidMedicalSlotDateTimeException extends RuntimeException {
    public InvalidMedicalSlotDateTimeException(String message) {
        super(message);
    }
}
