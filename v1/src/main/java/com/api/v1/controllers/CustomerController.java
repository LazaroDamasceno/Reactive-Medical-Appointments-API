package com.api.v1.controllers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.services.interfaces.customers.CustomerModificationService;
import com.api.v1.services.interfaces.customers.CustomerRegistrationService;
import com.api.v1.annotations.SSN;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.dtos.people.PersonRegistrationDto;
import com.api.v1.services.interfaces.customers.CustomerRetrievalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRegistrationService registrationService;
    private final CustomerModificationService modificationService;
    private final CustomerRetrievalService retrievalService;

    public CustomerController(
            CustomerRegistrationService registrationService,
            CustomerModificationService modificationService,
            CustomerRetrievalService retrievalService
    ) {
        this.registrationService = registrationService;
        this.modificationService = modificationService;
        this.retrievalService = retrievalService;
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

    @GetMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<CustomerResponseDto> findBySsn(@SSN @PathVariable String ssn) {
        return retrievalService.findBySsn(ssn);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CustomerResponseDto> findAll() {
        return retrievalService.findAll();
    }
}
