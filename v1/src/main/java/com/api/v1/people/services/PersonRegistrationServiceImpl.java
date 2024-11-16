package com.api.v1.people.services;

import com.api.v1.people.domain.Person;
import com.api.v1.people.domain.PersonRepository;
import com.api.v1.people.dtos.PersonRegistrationDto;
import com.api.v1.people.exceptions.DuplicatedEmailException;
import com.api.v1.people.exceptions.DuplicatedSsnException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonRegistrationServiceImpl implements PersonRegistrationService {

    private final PersonRepository personRepository;

    @Override
    public Mono<Person> register(@Valid PersonRegistrationDto registrationDto) {
        return personRepository
                .findBySsn(registrationDto.ssn())
                .hasElement()
                .flatMap(isSsnDuplicated -> {
                    if (isSsnDuplicated) return Mono.error(DuplicatedSsnException::new);
                    return personRepository
                            .findByEmail(registrationDto.email())
                            .hasElement()
                            .flatMap(isEmailDuplicated -> {
                                if (isEmailDuplicated) return Mono.error(DuplicatedEmailException::new);
                                return Mono.defer(() -> {
                                    Person person = Person.create(registrationDto);
                                    return personRepository.save(person);
                                });
                            });
                });
    }

}
