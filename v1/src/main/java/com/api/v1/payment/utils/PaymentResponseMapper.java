package com.api.v1.payment.utils;

import com.api.v1.cards.utils.CardResponseMapper;
import com.api.v1.payment.domain.Payment;
import com.api.v1.payment.dtos.PaymentResponseDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class PaymentResponseMapper {

    public static PaymentResponseDto mapToDto(Payment payment) {
        return new PaymentResponseDto(
                payment.number(),
                CardResponseMapper.mapToDto(payment.card()),
                payment.price(),
                ZonedDateTime.of(LocalDateTime.parse(payment.createdAt()), payment.createdAtZone())
        );
    }

}
