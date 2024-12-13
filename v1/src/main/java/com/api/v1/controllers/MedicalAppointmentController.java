package com.api.v1.controllers;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.annotations.OrderNumber;
import com.api.v1.annotations.SSN;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.services.medical_appointments.MedicalAppointmentBookingService;
import com.api.v1.services.medical_appointments.MedicalAppointmentCancellationService;
import com.api.v1.services.medical_appointments.MedicalAppointmentCompletionService;
import com.api.v1.services.medical_appointments.MedicalAppointmentRetrievalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentController {

    private final MedicalAppointmentBookingService bookingService;
    private final MedicalAppointmentCancellationService cancellationService;
    private final MedicalAppointmentCompletionService completionService;
    private final MedicalAppointmentRetrievalService retrievalService;

    public MedicalAppointmentController(
            MedicalAppointmentBookingService bookingService,
            MedicalAppointmentCancellationService cancellationService,
            MedicalAppointmentCompletionService completionService,
            MedicalAppointmentRetrievalService retrievalService, MedicalAppointmentRepository medicalAppointmentRepository
    ) {
        this.bookingService = bookingService;
        this.cancellationService = cancellationService;
        this.completionService = completionService;
        this.retrievalService = retrievalService;
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

    @GetMapping("{orderNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<MedicalAppointmentResponseDto> findByOrderNumber(@PathVariable @OrderNumber String orderNumber) {
        return retrievalService.findByOrderNumber(orderNumber);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAll() {
        return retrievalService.findAll();
    }

    @GetMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByCustomer(@PathVariable @SSN String ssn) {
        return retrievalService.findActiveCustomer(ssn);
    }

    @GetMapping("{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findAllByDoctor(medicalLicenseNumber);
    }

    @GetMapping("{ssn}/{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByCustomerAndDoctor(
            @PathVariable @SSN String ssn,
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findAllByCustomerAndDoctor(ssn, medicalLicenseNumber);
    }

    @GetMapping("active/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveCustomer(@PathVariable @SSN String ssn) {
        return retrievalService.findActiveCustomer(ssn);
    }

    @GetMapping("active/{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findActiveByDoctor(medicalLicenseNumber);
    }

    @GetMapping("active/{ssn}/{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByCustomerAndDoctor(
            @PathVariable @SSN String ssn,
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findActiveByCustomerAndDoctor(ssn, medicalLicenseNumber);
    }

    @GetMapping("completed/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCompletedCustomer(@PathVariable @SSN String ssn) {
        return retrievalService.findCompletedCustomer(ssn);
    }

    @GetMapping("completed/{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCompletedByDoctor(
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findCompletedByDoctor(medicalLicenseNumber);
    }

    @GetMapping("completed/{ssn}/{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCompletedByCustomerAndDoctor(
            @PathVariable @SSN String ssn,
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findCompletedByCustomerAndDoctor(ssn, medicalLicenseNumber);
    }

    @GetMapping("canceled/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCanceledCustomer(@PathVariable @SSN String ssn) {
        return retrievalService.findCanceledCustomer(ssn);
    }

    @GetMapping("canceled/{findCanceledByDoctor}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCanceledByDoctor(
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        return retrievalService.findCanceledByDoctor(medicalLicenseNumber);
    }

}
