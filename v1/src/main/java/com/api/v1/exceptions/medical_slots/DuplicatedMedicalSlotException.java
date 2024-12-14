package com.api.v1.exceptions.medical_slots;

public class DuplicatedMedicalSlotException extends RuntimeException {
    public DuplicatedMedicalSlotException() {
        super("The given datetime is already in use.");
    }
}
