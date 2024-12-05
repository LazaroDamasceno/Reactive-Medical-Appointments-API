package com.api.v1.people.internal.services;

import com.api.v1.people.Person;
import com.api.v1.people.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface PersonRegistrationService {
    Mono<Person> register(PersonRegistrationDto registrationDto);
}
