package com.api.v1.medical_appointment.services;

import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentService {
    Mono<MedicalAppointment> book(MedicalAppointmentBookingDto bookingDto);
}
