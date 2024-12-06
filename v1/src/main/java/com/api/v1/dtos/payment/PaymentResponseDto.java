package com.api.v1.dtos.payment;

import com.api.v1.dtos.cards.CardResponseDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
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
