package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonModificationDto;
import jakarta.validation.Valid;

public record DoctorModificationDto(
        @Valid DoctorLicenseNumberDto licenseNumberDto,
        @Valid PersonModificationDto modificationDto
) {
}
