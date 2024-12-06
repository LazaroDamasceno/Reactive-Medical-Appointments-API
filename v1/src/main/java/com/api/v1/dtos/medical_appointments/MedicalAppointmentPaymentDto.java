package com.api.v1.dtos.medical_appointments;

import com.api.v1.annotations.OrderNumber;
import jakarta.validation.constraints.Min;

public record MedicalAppointmentPaymentDto(
        @OrderNumber String cardNumber,
        @OrderNumber String appointmentOrderNumber,
        @Min(0) double price
) {
}
