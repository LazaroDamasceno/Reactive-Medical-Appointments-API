package com.api.v1.services.medical_appointments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentRetrievalService {
    Mono<MedicalAppointmentResponseDto> findByOrderNumber(String orderNumber);
    Flux<MedicalAppointmentResponseDto> findAll();
}
