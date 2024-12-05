package com.api.v1.services;

import com.api.v1.domain.Person;
import com.api.v1.dtos.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface PersonModificationService {
    Mono<Person> modify(Person person, PersonModificationDto modificationDto);
}
