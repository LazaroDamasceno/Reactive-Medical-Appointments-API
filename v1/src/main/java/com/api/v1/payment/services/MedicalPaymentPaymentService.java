package com.api.v1.payment.services;

import com.api.v1.payment.dtos.MedicalAppointmentPaymentDto;
import com.api.v1.payment.dtos.PaymentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalPaymentPaymentService {
    Mono<PaymentResponseDto> register(MedicalAppointmentPaymentDto paymentDto);
}
