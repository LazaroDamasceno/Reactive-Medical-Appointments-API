package com.api.v1.payment.utils;

import com.api.v1.cards.utils.CardResponseMapper;
import com.api.v1.medical_appointments.utils.MedicalAppointmentResponseMapper;
import com.api.v1.payment.domain.Payment;
import com.api.v1.payment.dtos.PaymentResponseDto;
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
