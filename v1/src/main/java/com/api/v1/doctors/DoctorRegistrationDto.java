package com.api.v1.doctors;

import com.api.v1.people.PersonRegistrationDto;
import jakarta.validation.Valid;

public record DoctorRegistrationDto(
        @MedicalLicenseNumber String licenseNumberDto,
        @Valid PersonRegistrationDto personRegistrationDto
) {
}
