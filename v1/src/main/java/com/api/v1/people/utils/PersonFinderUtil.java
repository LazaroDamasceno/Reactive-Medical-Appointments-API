package com.api.v1.people.utils;

import com.api.v1.people.annotations.SSN;
import com.api.v1.people.domain.Person;
import com.api.v1.people.domain.PersonRepository;
import com.api.v1.people.exceptions.NonExistentSsnException;
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
                .singleOptional()
                .flatMap(optional -> {
                    if (optional.isEmpty()) {
                        return Mono.error(NonExistentSsnException::new);
                    }
                    return Mono.just(optional.get());
                });
    }

}
