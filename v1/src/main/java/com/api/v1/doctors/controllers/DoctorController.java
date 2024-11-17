package com.api.v1.doctors.controllers;

import com.api.v1.doctors.dtos.DoctorModificationDto;
import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.doctors.services.DoctorModificationService;
import com.api.v1.doctors.services.DoctorRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController implements DoctorModificationService {

    private final DoctorRegistrationService registrationService;
    private final DoctorModificationService modificationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Mono<DoctorResponseDto> register(@Valid @RequestBody DoctorRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> modify(@Valid @RequestBody DoctorModificationDto modificationDto) {
        return modificationService.modify(modificationDto);
    }
}
