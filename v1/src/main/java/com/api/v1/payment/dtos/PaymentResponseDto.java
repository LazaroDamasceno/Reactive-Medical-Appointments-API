package com.api.v1.payment.dtos;

import com.api.v1.cards.dtos.CardResponseDto;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record PaymentResponseDto(
        ObjectId number,
        CardResponseDto cardResponseDto,
        BigDecimal price,
        ZonedDateTime payedAt
) {
}
