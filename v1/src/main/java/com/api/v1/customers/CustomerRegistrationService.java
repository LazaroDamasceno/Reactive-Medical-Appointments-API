package com.api.v1.customers;

import com.api.v1.people.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {
    Mono<CustomerResponseDto> register(PersonRegistrationDto registrationDto);
}
