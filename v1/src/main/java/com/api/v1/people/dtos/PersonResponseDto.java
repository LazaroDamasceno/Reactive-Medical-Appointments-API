package com.api.v1.people.dtos;

import java.time.LocalDate;

public record PersonResponseDto(
        FullNameDto fullNameDto,
        LocalDate birthDate,
        String ssn,
        String email,
        AddressDto address,
        String phoneNumber,
        String gender,
        String createdAt
) {
}
