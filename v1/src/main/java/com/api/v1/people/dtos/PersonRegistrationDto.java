package com.api.v1.people.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonRegistrationDto(
        @NotBlank String firstName,
        String middleName,
        @NotBlank String lastName,
        @NotBlank String ssn,
        LocalDate birthDate,
        @Valid AddressDto address,
        @NotBlank  @Size(min = 1) String email,
        @NotBlank @Size(min = 10, max = 10) String phoneNumber
) {
}
