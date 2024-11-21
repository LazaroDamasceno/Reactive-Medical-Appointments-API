package com.api.v1.cards.dtos;

import com.api.v1.people.dtos.PersonFullNameDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CardRegistrationDto(
        @Valid PersonFullNameDto ownersName,
        @NotBlank String ownerSsn,
        @NotNull LocalDate dueDate,
        @NotBlank @Size(min=3, max=3) String cvc
) {
}
