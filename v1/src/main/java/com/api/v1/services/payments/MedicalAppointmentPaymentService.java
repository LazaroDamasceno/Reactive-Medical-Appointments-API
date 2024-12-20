package com.api.v1.services.payments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentPaymentDto;
import com.api.v1.dtos.payment.PaymentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentPaymentService {
    Mono<PaymentResponseDto> payMedicalAppointment(MedicalAppointmentPaymentDto paymentDto);
}
