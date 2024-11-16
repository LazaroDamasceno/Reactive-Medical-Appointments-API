package com.api.v1.people.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonModificationDto(
        @Valid FullNameDto fullName,
        @NotNull LocalDate birthDate,
        @NotBlank String email,
        @Valid AddressDto address,
        @NotBlank String phoneNumber,
        @NotBlank String gender
) {
}
