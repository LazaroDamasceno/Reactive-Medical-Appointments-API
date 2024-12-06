package com.api.v1.services.interfaces.customers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.dtos.people.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {
    Mono<CustomerResponseDto> register(PersonRegistrationDto registrationDto);
}
