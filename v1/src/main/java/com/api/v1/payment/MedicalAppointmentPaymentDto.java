package com.api.v1.payment;

import com.api.v1.medical_appointments.OrderNumber;
import jakarta.validation.constraints.Min;

public record MedicalAppointmentPaymentDto(
        @OrderNumber String cardNumber,
        @OrderNumber String appointmentOrderNumber,
        @Min(0) double price
) {
}
