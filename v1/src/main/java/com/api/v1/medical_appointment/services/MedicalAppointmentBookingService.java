package com.api.v1.medical_appointment.services;

import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {
    Mono<MedicalAppointment> book(MedicalAppointmentBookingDto bookingDto);
}
