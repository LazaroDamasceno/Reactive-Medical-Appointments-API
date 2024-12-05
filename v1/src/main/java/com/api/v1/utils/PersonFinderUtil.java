package com.api.v1.utils;

import com.api.v1.domain.Person;
import com.api.v1.domain.PersonRepository;
import com.api.v1.annotations.SSN;
import com.api.v1.exceptions.NonExistentSsnException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PersonFinderUtil {

    private final PersonRepository personRepository;

    public PersonFinderUtil(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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
