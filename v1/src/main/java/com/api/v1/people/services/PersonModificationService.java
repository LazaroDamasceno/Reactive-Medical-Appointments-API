package com.api.v1.people.services;


import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface PersonModificationService {
    Mono<Void> modify(Person person, PersonModificationDto modificationDto);
}
