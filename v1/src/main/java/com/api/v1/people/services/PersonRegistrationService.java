package com.api.v1.people.services;

import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface PersonRegistrationService {
    Mono<String> register(PersonRegistrationDto registrationDto);
}
