package com.api.v1.people.services;

import com.api.v1.people.Person;
import com.api.v1.people.PersonRegistrationDto;
import com.api.v1.people.PersonRegistrationService;
import com.api.v1.people.PersonRepository;
import com.api.v1.people.exceptions.DuplicatedEmailException;
import com.api.v1.people.exceptions.DuplicatedSsnException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonRegistrationServiceImpl implements PersonRegistrationService {

    private final PersonRepository personRepository;

    public PersonRegistrationServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Mono<Person> register(@Valid PersonRegistrationDto registrationDto) {
        return personRepository
                .findBySsn(registrationDto.ssn())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(DuplicatedSsnException::new);
                    }
                    return personRepository
                            .findByEmail(registrationDto.email())
                            .hasElement()
                            .flatMap(exists2 -> {
                                if (exists2) {
                                    return Mono.error(DuplicatedEmailException::new);
                                }
                                return Mono.defer(() -> {
                                   Person person = Person .create(registrationDto);
                                   return personRepository.save(person);
                                });
                            });
                });
    }

}
