package com.api.v1.services.people;

import com.api.v1.domain.people.Person;
import com.api.v1.dtos.people.PersonRegistrationDto;
import reactor.core.publisher.Mono;

public interface PersonRegistrationService {
    Mono<Person> register(PersonRegistrationDto registrationDto);
}
