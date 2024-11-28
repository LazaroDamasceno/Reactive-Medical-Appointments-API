package com.api.v1.customers.dtos;

import com.api.v1.people.dtos.PersonResponseDto;

import java.time.LocalDateTime;
import java.time.ZoneId;

public record CustomerResponseDto(
        PersonResponseDto personResponseDto,
        LocalDateTime createdAt,
        ZoneId createdAtZone
) {
}
