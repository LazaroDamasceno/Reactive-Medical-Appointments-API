package com.api.v1.services;

import com.api.v1.dtos.MedicalAppointmentPaymentDto;
import com.api.v1.dtos.PaymentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentPaymentService {
    Mono<PaymentResponseDto> payMedicalAppointment(MedicalAppointmentPaymentDto paymentDto);
}
