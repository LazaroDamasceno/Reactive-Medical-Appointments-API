package com.api.v1.people.dtos;

import com.api.v1.people.annotations.SSN;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonRegistrationDto(
        @Valid FullNameDto fullName,
        @NotNull LocalDate birthDate,
        @SSN String ssn,
        @NotBlank @Email String email,
        @Valid AddressDto address,
        @NotBlank @Size(min = 10, max = 10, message = "Phone number has 10 digits.")
        String phoneNumber,
        @NotBlank @Size(min = 1) String gender
) {
}
