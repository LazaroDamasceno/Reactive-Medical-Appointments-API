package com.api.v1.dtos;

import java.time.LocalDate;

public record CardResponseDto(
        String type,
        LocalDate dueDate,
        String cvc,
        String ownerName,
        String OwnerSsn
) {
}
