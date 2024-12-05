package com.api.v1.dtos;

import com.api.v1.annotations.SSN;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonRegistrationDto(
        @NotBlank String firstName,
        String middleName,
        @NotBlank String lastName,
        @NotNull LocalDate birthDate,
        @SSN String ssn,
        @NotBlank @Email String email,
        @Valid PersonAddressDto address,
        @NotBlank @Size(min = 10, max = 10, message = "Phone number has 10 digits.")
        String phoneNumber,
        @NotBlank @Size(min = 1) String gender
) {
}
