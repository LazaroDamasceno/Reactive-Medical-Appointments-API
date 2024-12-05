package com.api.v1.services;

import com.api.v1.domain.Person;
import com.api.v1.dtos.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface PersonRegistrationService {
    Mono<Person> register(PersonRegistrationDto registrationDto);
}
