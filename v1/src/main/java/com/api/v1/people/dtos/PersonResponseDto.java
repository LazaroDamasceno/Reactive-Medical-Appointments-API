package com.api.v1.people.dtos;

import com.api.v1.people.annotations.SSN;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonResponseDto(
        @Valid FullNameDto fullNameDto,
        @NotNull LocalDate birthDate,
        @SSN String ssn,
        @NotBlank String email,
        @Valid AddressDto address,
        @NotBlank String phoneNumber,
        @NotBlank String gender,
        @NotBlank String createdAt
) {
}
