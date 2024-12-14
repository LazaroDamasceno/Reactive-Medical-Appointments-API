package com.api.v1.dtos.medical_appointments;

import com.api.v1.annotations.MongoDbId;
import jakarta.validation.constraints.Min;

public record MedicalAppointmentPaymentDto(
        @MongoDbId String cardNumber,
        @MongoDbId String appointmentid,
        @Min(0) double price
) {
}
