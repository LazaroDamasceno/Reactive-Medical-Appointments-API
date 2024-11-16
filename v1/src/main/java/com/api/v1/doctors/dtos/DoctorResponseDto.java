package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DoctorResponseDto(
        @Valid DoctorLicenseNumberDto licenseNumber,
        @Valid PersonResponseDto personResponseDto,
        @NotBlank String hiredAt,
        String terminatedAt
) {
}
