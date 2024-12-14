package com.api.v1.exceptions.medical_slots;

public class ImmutableMedicalSlotException extends RuntimeException {
    public ImmutableMedicalSlotException() {
        super("The sought medical slot is already canceled.");
    }
}
