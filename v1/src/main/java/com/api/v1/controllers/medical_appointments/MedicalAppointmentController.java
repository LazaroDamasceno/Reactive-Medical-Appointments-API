package com.api.v1.controllers.medical_appointments;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.annotations.SSN;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.services.medical_appointments.MedicalAppointmentBookingService;
import com.api.v1.services.medical_appointments.MedicalAppointmentCancellationService;
import com.api.v1.services.medical_appointments.MedicalAppointmentCompletionService;
import com.api.v1.services.medical_appointments.MedicalAppointmentRetrievalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentController {

    @Autowired
    private MedicalAppointmentBookingService bookingService;

    @Autowired
    private MedicalAppointmentCancellationService cancellationService;

    @Autowired
    private MedicalAppointmentCompletionService completionService;

    @Autowired
    private MedicalAppointmentRetrievalService retrievalService;

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

    @PatchMapping("{id}/cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> cancel(@MongoDbId @PathVariable String id) {
        return cancellationService.cancel(id);
    }

    @PatchMapping("{id}/completion")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> complete(@MongoDbId @PathVariable String id) {
        return completionService.complete(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<MedicalAppointmentResponseDto> findByid(@PathVariable @MongoDbId String id) {
        return retrievalService.findByid(id);
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
