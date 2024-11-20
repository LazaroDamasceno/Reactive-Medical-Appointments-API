package com.api.v1.people.dtos;

import jakarta.validation.constraints.NotBlank;

public record PersonFullNameDto(
        @NotBlank String firstName,
        String middleName,
        @NotBlank String lastName
) {
}
