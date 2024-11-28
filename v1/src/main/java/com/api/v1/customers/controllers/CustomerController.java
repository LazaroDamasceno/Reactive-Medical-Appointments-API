package com.api.v1.customers.controllers;

import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.customers.services.CustomerModificationService;
import com.api.v1.customers.services.CustomerRegistrationService;
import com.api.v1.people.annotations.SSN;
import com.api.v1.people.dtos.PersonModificationDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRegistrationService registrationService;
    private final CustomerModificationService modificationService;

    @PostMapping("paid")
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
