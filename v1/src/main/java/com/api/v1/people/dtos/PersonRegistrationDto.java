package com.api.v1.people.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PersonRegistrationDto(
        @Valid FullNameDto fullName,
        @NotBlank String ssn,
        @NotNull Date birthDate,
        @Valid AddressDto address,
        @NotBlank  @Size(min = 1) String email,
        @NotBlank @Size(min = 10, max = 10) String phoneNumber
) {
}
