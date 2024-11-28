package com.api.v1.medical_appointment.services;

import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {
    Mono<MedicalAppointment> bookPaidMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointment> bookAffordableMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
    Mono<MedicalAppointment> bookPrivateHeathCareMedicalAppointment(MedicalAppointmentBookingDto bookingDto);
}
