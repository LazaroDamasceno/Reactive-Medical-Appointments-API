package com.api.v1.dtos;

import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record PaymentResponseDto(
        ObjectId number,
        CardResponseDto cardResponseDto,
        BigDecimal price,
        MedicalAppointmentResponseDto medicalAppointmentResponseDto,
        ZonedDateTime payedAt
) {
}
