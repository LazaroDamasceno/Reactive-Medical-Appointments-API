package com.api.v1.payment;

import com.api.v1.cards.CardResponseDto;
import com.api.v1.medical_appointments.MedicalAppointmentResponseDto;
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
