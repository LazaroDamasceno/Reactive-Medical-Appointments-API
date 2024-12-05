package com.api.v1.payment;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentPaymentService {
    Mono<PaymentResponseDto> payMedicalAppointment(MedicalAppointmentPaymentDto paymentDto);
}
