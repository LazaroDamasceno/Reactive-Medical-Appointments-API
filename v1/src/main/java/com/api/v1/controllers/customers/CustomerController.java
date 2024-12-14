package com.api.v1.controllers.customers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.services.customers.CustomerModificationService;
import com.api.v1.services.customers.CustomerRegistrationService;
import com.api.v1.annotations.SSN;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.dtos.people.PersonRegistrationDto;
import com.api.v1.services.customers.CustomerRetrievalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRegistrationService registrationService;

    @Autowired
    private CustomerModificationService modificationService;

    @Autowired
    private CustomerRetrievalService retrievalService;

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
