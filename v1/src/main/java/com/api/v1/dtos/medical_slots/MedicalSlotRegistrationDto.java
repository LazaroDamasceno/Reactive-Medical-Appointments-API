package com.api.v1.dtos.medical_slots;

import com.api.v1.annotations.MedicalLicenseNumber;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MedicalSlotRegistrationDto(
   @MedicalLicenseNumber String medicalLicenseNumber,
   @NotNull LocalDateTime availableAt
) {
}
