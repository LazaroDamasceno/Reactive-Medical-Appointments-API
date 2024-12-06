package com.api.v1.dtos.cards;

import java.time.LocalDate;

public record CardResponseDto(
        String type,
        LocalDate dueDate,
        String cvc,
        String ownerName,
        String OwnerSsn
) {
}
