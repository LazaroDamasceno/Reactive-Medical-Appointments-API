package com.api.v1.dtos.medical_appointments;

import com.api.v1.annotations.MongoObjectId;
import jakarta.validation.constraints.Min;

public record MedicalAppointmentPaymentDto(
        @MongoObjectId String cardNumber,
        @MongoObjectId String appointmentOrderNumber,
        @Min(0) double price
) {
}
