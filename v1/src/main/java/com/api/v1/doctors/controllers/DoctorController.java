package com.api.v1.doctors.controllers;

import com.api.v1.doctors.annotations.MedicalLicenseNumber;
import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.doctors.services.DoctorModificationService;
import com.api.v1.doctors.services.DoctorRegistrationService;
import com.api.v1.doctors.services.DoctorTerminationService;
import com.api.v1.people.dtos.PersonModificationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRegistrationService registrationService;
    private final DoctorModificationService modificationService;
    private final DoctorTerminationService terminationService;

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
