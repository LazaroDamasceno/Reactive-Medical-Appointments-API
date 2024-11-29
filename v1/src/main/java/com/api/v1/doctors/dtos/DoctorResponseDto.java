package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonResponseDto;
import org.bson.BsonDateTime;

import java.time.ZoneId;

public record DoctorResponseDto(
        String licenseNumber,
        PersonResponseDto personResponseDto,
        BsonDateTime hiredAt,
        ZoneId hiredAtZone,
        BsonDateTime terminatedAt,
        ZoneId terminatedAtZone
) {
}
