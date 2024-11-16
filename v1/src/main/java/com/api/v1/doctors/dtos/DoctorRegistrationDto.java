package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DoctorRegistrationDto(
        @Valid PersonRegistrationDto personRegistrationDto,
        String licenseNumber,
        @NotBlank String specialty
) {
}
