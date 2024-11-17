package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;

public record DoctorRegistrationDto(
        @Valid DoctorLicenseNumberDto licenseNumberDto,
        @Valid PersonRegistrationDto personRegistrationDto
) {
}
