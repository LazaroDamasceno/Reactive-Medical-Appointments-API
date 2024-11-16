package com.api.v1.doctors.dtos;

import jakarta.validation.constraints.NotBlank;

public record DoctorRegistrationDto(
        @NotBlank String personId,
        String licenseNumber,
        @NotBlank String speciality
) {
}
