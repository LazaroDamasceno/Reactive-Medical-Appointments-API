package com.api.v1.medical_appointments.controllers;

import com.api.v1.medical_appointments.annotation.OrderNumber;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentBookingDto;
import com.api.v1.medical_appointments.services.MedicalAppointmentBookingService;
import com.api.v1.medical_appointments.services.MedicalAppointmentCancellationService;
import com.api.v1.medical_appointments.services.MedicalAppointmentCompletionService;
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


    public Mono<MedicalAppointment> book(@Valid @RequestBody MedicalAppointmentBookingDto bookingDto) {
        return bookingService.bookPaidMedicalAppointment(bookingDto);
    }

    @PostMapping("paid")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointment> bookPaidMedicalAppointment(
            @RequestBody @Valid MedicalAppointmentBookingDto bookingDto
    ) {
        return bookingService.bookPaidMedicalAppointment(bookingDto);
    }

    @PostMapping("affordable")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointment> bookAffordableMedicalAppointment(
            @RequestBody @Valid MedicalAppointmentBookingDto bookingDto
    ) {
        return bookingService.bookAffordableMedicalAppointment(bookingDto);
    }

    @PostMapping("private-health-care")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointment> bookPrivateHeathCareMedicalAppointment(
            @RequestBody @Valid MedicalAppointmentBookingDto bookingDto
    ) {
        return bookingService.bookPrivateHeathCareMedicalAppointment(bookingDto);
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
