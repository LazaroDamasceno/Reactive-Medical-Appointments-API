package com.api.v1.people;

import reactor.core.publisher.Mono;

public interface PersonModificationService {
    Mono<Person> modify(Person person, PersonModificationDto modificationDto);
}
