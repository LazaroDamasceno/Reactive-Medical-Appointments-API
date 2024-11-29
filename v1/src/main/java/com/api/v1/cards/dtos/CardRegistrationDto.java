package com.api.v1.cards.dtos;

import java.time.LocalDate;

public record CardRegistrationDto(
        LocalDate dueDate,
        String cvc,
        String ownerName,
        String ssnOwner
) {
}
