package com.api.v1.payment.dtos;

import com.api.v1.cards.dtos.CardResponseDto;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto;
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
