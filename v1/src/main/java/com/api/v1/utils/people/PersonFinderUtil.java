package com.api.v1.utils.people;

import com.api.v1.domain.people.Person;
import com.api.v1.domain.people.PersonRepository;
import com.api.v1.annotations.SSN;
import com.api.v1.exceptions.people.NonExistentSsnException;
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
                .switchIfEmpty(Mono.error(NonExistentSsnException::new))
                .single();
    }

}
