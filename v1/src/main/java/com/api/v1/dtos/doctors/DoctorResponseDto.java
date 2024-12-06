package com.api.v1.dtos.doctors;

import com.api.v1.dtos.people.PersonResponseDto;

import java.time.ZoneId;

public record DoctorResponseDto(
        String licenseNumber,
        PersonResponseDto personResponseDto,
        String hiredAt,
        ZoneId hiredAtZone,
        String terminatedAt,
        ZoneId terminatedAtZone
) {
}
