package com.api.v1.controllers;

import com.api.v1.annotations.OrderNumber;
import com.api.v1.dtos.MedicalAppointmentBookingDto;
import com.api.v1.dtos.MedicalAppointmentResponseDto;
import com.api.v1.services.MedicalAppointmentBookingService;
import com.api.v1.services.MedicalAppointmentCancellationService;
import com.api.v1.services.MedicalAppointmentCompletionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentController {

    private final MedicalAppointmentBookingService bookingService;
    private final MedicalAppointmentCancellationService cancellationService;
    private final MedicalAppointmentCompletionService completionService;

    public MedicalAppointmentController(
            MedicalAppointmentBookingService bookingService,
            MedicalAppointmentCancellationService cancellationService,
            MedicalAppointmentCompletionService completionService
    ) {
        this.bookingService = bookingService;
        this.cancellationService = cancellationService;
        this.completionService = completionService;
    }

    public Mono<MedicalAppointmentResponseDto> book(@Valid @RequestBody MedicalAppointmentBookingDto bookingDto) {
        return bookingService.bookPaidMedicalAppointment(bookingDto);
    }

    @PostMapping("paid")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointmentResponseDto> bookPaidMedicalAppointment(
            @RequestBody @Valid MedicalAppointmentBookingDto bookingDto
    ) {
        return bookingService.bookPaidMedicalAppointment(bookingDto);
    }

    @PostMapping("affordable")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointmentResponseDto> bookAffordableMedicalAppointment(
            @RequestBody @Valid MedicalAppointmentBookingDto bookingDto
    ) {
        return bookingService.bookAffordableMedicalAppointment(bookingDto);
    }

    @PostMapping("private-health-care")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointmentResponseDto> bookPrivateHeathCareMedicalAppointment(
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
