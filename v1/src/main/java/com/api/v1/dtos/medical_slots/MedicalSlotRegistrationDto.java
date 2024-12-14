package com.api.v1.dtos.medical_slots;

import java.time.LocalDateTime;

public record MedicalSlotRegistrationDto(
   String medicalLicenseNumber,
   LocalDateTime availableAt
) {
}
