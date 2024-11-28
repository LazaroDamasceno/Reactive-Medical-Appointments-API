package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonResponseDto;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record DoctorResponseDto(
        String licenseNumber,
        PersonResponseDto personResponseDto,
        LocalDateTime hiredAt,
        ZoneId hiredAtAt,
        LocalDateTime terminatedAt,
        ZoneId terminatedAtZone
) {
}
