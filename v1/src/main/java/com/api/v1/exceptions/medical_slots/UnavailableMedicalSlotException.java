package com.api.v1.exceptions.medical_slots;


public class UnavailableMedicalSlotException extends RuntimeException {
    public UnavailableMedicalSlotException(String message) {
        super(message);
    }
}
