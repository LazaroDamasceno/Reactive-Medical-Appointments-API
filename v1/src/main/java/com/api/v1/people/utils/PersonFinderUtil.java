package com.api.v1.people.utils;

import com.api.v1.people.annotations.SSN;
import com.api.v1.people.domain.Person;
import com.api.v1.people.domain.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonFinderUtil {

    private final PersonRepository personRepository;

    public Mono<Person> find(@SSN String ssn) {
        return personRepository
                .findBySsn(ssn)
                .flatMap(Mono::just);
    }

}
