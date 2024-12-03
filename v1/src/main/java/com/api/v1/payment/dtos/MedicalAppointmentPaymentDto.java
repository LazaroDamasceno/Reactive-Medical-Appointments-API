package com.api.v1.payment.dtos;

import com.api.v1.medical_appointments.annotation.OrderNumber;

public record MedicalAppointmentPaymentDto(
        @OrderNumber String cardNumber,
        @OrderNumber String appointmentOrderNumber,
        double price
) {
}
