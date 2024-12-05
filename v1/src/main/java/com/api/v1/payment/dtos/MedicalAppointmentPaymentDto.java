package com.api.v1.payment.dtos;

import com.api.v1.medical_appointments.annotation.OrderNumber;
import jakarta.validation.constraints.Min;

public record MedicalAppointmentPaymentDto(
        @OrderNumber String cardNumber,
        @OrderNumber String appointmentOrderNumber,
        @Min(0) double price
) {
}
