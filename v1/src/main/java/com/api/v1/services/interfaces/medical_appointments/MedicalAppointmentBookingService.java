package com.api.v1.services.interfaces.medical_appointments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {
    Mono<MedicalAppointmentResponseDto> bookPaidMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookAffordableMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointmentResponseDto> bookPrivateHeathCareMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
}
