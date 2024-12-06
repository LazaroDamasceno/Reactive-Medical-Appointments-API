package com.api.v1.dtos.doctors;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.dtos.people.PersonRegistrationDto;
import jakarta.validation.Valid;

public record DoctorRegistrationDto(
        @MedicalLicenseNumber String licenseNumberDto,
        @Valid PersonRegistrationDto personRegistrationDto
) {
}
