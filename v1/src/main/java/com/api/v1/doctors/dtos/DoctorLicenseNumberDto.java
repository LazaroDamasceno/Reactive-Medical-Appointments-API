package com.api.v1.doctors.dtos;

import jakarta.validation.constraints.NotBlank;

public record DoctorLicenseNumberDto(
        @NotBlank String licenseNumber,
        @NotBlank String state
) {
}
