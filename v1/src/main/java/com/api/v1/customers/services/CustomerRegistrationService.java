package com.api.v1.customers.services;

import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {
    Mono<CustomerResponseDto> register(PersonRegistrationDto registrationDto);
}
