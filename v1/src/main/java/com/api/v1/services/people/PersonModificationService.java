package com.api.v1.services.people;

import com.api.v1.domain.people.Person;
import com.api.v1.dtos.people.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface PersonModificationService {
    Mono<Person> modify(Person person, PersonModificationDto modificationDto);
}
