package com.api.v1.dtos;

import com.api.v1.annotations.MedicalLicenseNumber;
import jakarta.validation.Valid;

public record DoctorRegistrationDto(
        @MedicalLicenseNumber String licenseNumberDto,
        @Valid PersonRegistrationDto personRegistrationDto
) {
}
