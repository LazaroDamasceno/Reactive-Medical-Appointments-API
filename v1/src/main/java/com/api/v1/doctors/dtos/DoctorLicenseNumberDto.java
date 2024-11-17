package com.api.v1.doctors.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorLicenseNumberDto(
        @NotBlank String licenseNumber,
        @NotBlank @Size(min = 2, max = 2) String state
) {
}
