package com.api.v1.controllers.medical_slots;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.dtos.medical_slots.MedicalSlotRegistrationDto;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import com.api.v1.services.medical_slots.MedicalSlotCancellationService;
import com.api.v1.services.medical_slots.MedicalSlotRegistrationService;
import com.api.v1.services.medical_slots.MedicalSlotRetrievalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-slots")
public class MedicalSlotController {

    private final MedicalSlotRegistrationService registrationService;
    private final MedicalSlotCancellationService cancellationService;
    private final MedicalSlotRetrievalService retrievalService;

    public MedicalSlotController(
            MedicalSlotRegistrationService registrationService,
            MedicalSlotCancellationService cancellationService,
            MedicalSlotRetrievalService retrievalService
    ) {
        this.registrationService = registrationService;
        this.cancellationService = cancellationService;
        this.retrievalService = retrievalService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalSlotResponseDto> register(@Valid @RequestBody MedicalSlotRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    @PatchMapping("{id}/cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> cancel(@MongoDbId @PathVariable String id) {
        return cancellationService.cancel(id);
    }

    @GetMapping("{id}")
    public Mono<MedicalSlotResponseDto> find(@MongoDbId @PathVariable String id) {
        return null;
    }

    @GetMapping
    public Flux<MedicalSlotResponseDto> findAll() {
        return retrievalService.findAll();
    }

    @GetMapping("{medicalLicenseNumber}")
    public Flux<MedicalSlotResponseDto> findAll(@PathVariable @MedicalLicenseNumber String medicalLicenseNumber) {
        return retrievalService.findAll(medicalLicenseNumber);
    }

    @GetMapping("{medicalLicenseNumber}/active")
    public Flux<MedicalSlotResponseDto> findActive(@PathVariable @MedicalLicenseNumber String medicalLicenseNumber) {
        return retrievalService.findActive(medicalLicenseNumber);
    }

    @GetMapping("{medicalLicenseNumber}/completed")
    public Flux<MedicalSlotResponseDto> findCompleted(@PathVariable @MedicalLicenseNumber String medicalLicenseNumber) {
        return retrievalService.findCompleted(medicalLicenseNumber);
    }

    @GetMapping("{medicalLicenseNumber}/canceled")
    public Flux<MedicalSlotResponseDto> findCanceled(@PathVariable @MedicalLicenseNumber String medicalLicenseNumber) {
        return retrievalService.findCanceled(medicalLicenseNumber);
    }
}
