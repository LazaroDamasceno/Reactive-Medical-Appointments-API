package com.api.v1.services;

import com.api.v1.dtos.MedicalAppointmentBookingDto;
import com.api.v1.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {
    Mono<MedicalAppointmentResponseDto> bookPaidMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookAffordableMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookPrivateHeathCareMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
}
