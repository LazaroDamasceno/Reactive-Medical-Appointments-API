package com.api.v1.utils.payments;

import com.api.v1.domain.payments.Payment;
import com.api.v1.dtos.payment.PaymentResponseDto;
import com.api.v1.utils.cards.CardResponseMapper;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class PaymentResponseMapper {

    public static PaymentResponseDto mapToDto(Payment payment) {
        return new PaymentResponseDto(
                payment.number(),
                CardResponseMapper.mapToDto(payment.card()),
                payment.price(),
                MedicalAppointmentResponseMapper.mapToDto(payment.medicalAppointment()),
                ZonedDateTime.of(LocalDateTime.parse(payment.createdAt()), payment.createdAtZone())
        );
    }

    public static Mono<PaymentResponseDto> mapToMono(Payment payment) {
        return Mono.just(mapToDto(payment));
    }

}
