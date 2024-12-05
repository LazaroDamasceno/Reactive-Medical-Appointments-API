package com.api.v1.controllers;

import com.api.v1.dtos.CustomerResponseDto;
import com.api.v1.services.CustomerModificationService;
import com.api.v1.services.CustomerRegistrationService;
import com.api.v1.annotations.SSN;
import com.api.v1.dtos.PersonModificationDto;
import com.api.v1.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRegistrationService registrationService;
    private final CustomerModificationService modificationService;

    public CustomerController(
            CustomerRegistrationService registrationService,
            CustomerModificationService modificationService
    ) {
        this.registrationService = registrationService;
        this.modificationService = modificationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CustomerResponseDto> register(@Valid @RequestBody PersonRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    @PutMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> modify(
            @SSN @PathVariable String ssn,
            @Valid @RequestBody PersonModificationDto modificationDto
    ) {
        return modificationService.modify(ssn, modificationDto);
    }
}
