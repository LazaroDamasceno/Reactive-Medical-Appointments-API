package com.api.v1.dtos;

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
