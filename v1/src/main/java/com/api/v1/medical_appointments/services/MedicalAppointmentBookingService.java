package com.api.v1.medical_appointments.services;

import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentBookingDto;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {
    Mono<MedicalAppointmentResponseDto> bookPaidMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookAffordableMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookPrivateHeathCareMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
}
