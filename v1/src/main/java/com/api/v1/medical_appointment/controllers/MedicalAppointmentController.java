package com.api.v1.medical_appointment.controllers;

import com.api.v1.medical_appointment.annotation.OrderNumber;
import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import com.api.v1.medical_appointment.services.MedicalAppointmentBookingService;
import com.api.v1.medical_appointment.services.MedicalAppointmentCancellationService;
import com.api.v1.medical_appointment.services.MedicalAppointmentCompletionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentBookingService bookingService;
    private final MedicalAppointmentCancellationService cancellationService;
    private final MedicalAppointmentCompletionService completionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointment> book(@Valid @RequestBody MedicalAppointmentBookingDto bookingDto) {
        return bookingService.bookPaidMedicalAppointment(bookingDto);
    }

    @PatchMapping("{orderNumber}/cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> cancel(@OrderNumber @PathVariable String orderNumber) {
        return cancellationService.cancel(orderNumber);
    }

    @PatchMapping("{orderNumber}/completion")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> complete(@OrderNumber @PathVariable String orderNumber) {
        return completionService.complete(orderNumber);
    }
}
