package com.api.v1.doctors.dtos;

import com.api.v1.people.domain.Person;

public record DoctorResponseDto(
        Person person,
        String doctorLicenseNumber,
        String speciality,
        String createdAt
) {
}
