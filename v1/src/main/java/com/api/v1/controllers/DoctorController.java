package com.api.v1.controllers;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.dtos.doctors.DoctorRegistrationDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.services.doctors.DoctorModificationService;
import com.api.v1.services.doctors.DoctorRegistrationService;
import com.api.v1.services.doctors.DoctorRetrievalService;
import com.api.v1.services.doctors.DoctorTerminationService;
import com.api.v1.dtos.people.PersonModificationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorRegistrationService registrationService;
    private final DoctorModificationService modificationService;
    private final DoctorTerminationService terminationService;
    private final DoctorRetrievalService retrievalService;

    public DoctorController(
            DoctorRegistrationService registrationService,
            DoctorModificationService modificationService,
            DoctorTerminationService terminationService,
            DoctorRetrievalService retrievalService
    ) {
        this.registrationService = registrationService;
        this.modificationService = modificationService;
        this.terminationService = terminationService;
        this.retrievalService = retrievalService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<DoctorResponseDto> register(@Valid @RequestBody DoctorRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    @PutMapping("{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> modify(
            @PathVariable @MedicalLicenseNumber String medicalLicenseNumber,
            @Valid @RequestBody PersonModificationDto modificationDto
    ) {
        return modificationService.modify(medicalLicenseNumber, modificationDto);
    }

    @PatchMapping("{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> terminate(@PathVariable @MedicalLicenseNumber String medicalLicenseNumber) {
        return terminationService.terminate(medicalLicenseNumber);
    }

    @GetMapping("{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<DoctorResponseDto> findByMedicalLicenseNumber(
            @MedicalLicenseNumber @PathVariable String medicalLicenseNumber
    ) {
        return retrievalService.findByMedicalLicenseNumber(medicalLicenseNumber);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<DoctorResponseDto> findAll() {
        return retrievalService.findAll();
    }
}
