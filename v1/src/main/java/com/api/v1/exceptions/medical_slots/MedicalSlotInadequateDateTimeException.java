package com.api.v1.exceptions.medical_slots;

public class MedicalSlotInadequateDateTimeException extends RuntimeException {
    public MedicalSlotInadequateDateTimeException() {
        super("The given datetime must, at least, be today.");
    }
}
