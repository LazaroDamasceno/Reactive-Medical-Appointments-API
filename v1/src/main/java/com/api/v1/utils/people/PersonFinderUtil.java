package com.api.v1.utils.people;

import com.api.v1.domain.people.Person;
import com.api.v1.domain.people.PersonRepository;
import com.api.v1.annotations.SSN;
import com.api.v1.exceptions.people.NonExistentSsnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PersonFinderUtil {

    @Autowired
    private PersonRepository personRepository;

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
