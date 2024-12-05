package com.api.v1.doctors.controllers;

import com.api.v1.doctors.MedicalLicenseNumber;
import com.api.v1.doctors.DoctorRegistrationDto;
import com.api.v1.doctors.DoctorResponseDto;
import com.api.v1.doctors.DoctorModificationService;
import com.api.v1.doctors.DoctorRegistrationService;
import com.api.v1.doctors.DoctorTerminationService;
import com.api.v1.people.PersonModificationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorRegistrationService registrationService;
    private final DoctorModificationService modificationService;
    private final DoctorTerminationService terminationService;

    public DoctorController(
            DoctorRegistrationService registrationService,
            DoctorModificationService modificationService,
            DoctorTerminationService terminationService
    ) {
        this.registrationService = registrationService;
        this.modificationService = modificationService;
        this.terminationService = terminationService;
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
}
