package com.api.v1.people;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonModificationDto(
        @NotBlank String firstName,
        String middleName,
        @NotBlank String lastName,
        @NotNull LocalDate birthDate,
        @NotBlank String email,
        @Valid PersonAddressDto address,
        @NotBlank String phoneNumber,
        @NotBlank String gender
) {
}
