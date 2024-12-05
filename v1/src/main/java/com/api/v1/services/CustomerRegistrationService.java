package com.api.v1.services;

import com.api.v1.dtos.CustomerResponseDto;
import com.api.v1.dtos.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface CustomerRegistrationService {
    Mono<CustomerResponseDto> register(PersonRegistrationDto registrationDto);
}
