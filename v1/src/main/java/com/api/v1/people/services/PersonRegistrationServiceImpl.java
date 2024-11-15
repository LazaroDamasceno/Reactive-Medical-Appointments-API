package com.api.v1.people.services;

import com.api.v1.db.DbSets;
import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PersonRegistrationServiceImpl implements PersonRegistrationService {

    @Override
    public Mono<Person> register(@Valid PersonRegistrationDto registrationDto) {
        return Mono.defer(() -> {
            Person person = Person.create(registrationDto);
            DbSets.peopleCollection()
                    .document()
                    .set(person);
            return Mono.just(person);
        });
    }

}
