package com.api.v1.doctors.domain;

import com.api.v1.doctors.dtos.DoctorRegistrationDto;

import java.time.ZonedDateTime;

public record Doctor(
        String personId,
        String licenseNumber,
        String speciality,
        String createdAt
) {

    public static Doctor create(DoctorRegistrationDto registrationDto) {
        return new Doctor(
                registrationDto.personId(),
                registrationDto.licenseNumber(),
                registrationDto.speciality(),
                ZonedDateTime.now().toString()
        );
    }

}
