package com.api.v1.dtos.people;

import java.time.LocalDate;

public record PersonResponseDto(
        String fullNameDto,
        LocalDate birthDate,
        String ssn,
        String email,
        PersonAddressDto address,
        String phoneNumber,
        String gender
) {
}
