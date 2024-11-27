package com.api.v1.medical_appointment.controllers;

import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import com.api.v1.medical_appointment.services.MedicalAppointmentBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentBookingService bookingService;

    @PostMapping
    public Mono<MedicalAppointment> book(@Valid @RequestBody MedicalAppointmentBookingDto bookingDto) {
        return bookingService.book(bookingDto);
    }
}
