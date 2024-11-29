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
                .singleOptional()
                .zipWith(personRepository
                        .findByEmail(registrationDto.email())
                        .singleOptional()
                )
                .flatMap(tuple -> {
                   if (tuple.getT1().isPresent()) {
                       return Mono.error(DuplicatedSsnException::new);
                   }
                   if (tuple.getT2().isPresent()) {
                        return Mono.error(DuplicatedEmailException::new);
                   }
                   return Mono.defer(() -> {
                      Person person = Person.create(registrationDto);
                      return personRepository.save(person);
                   });
                });
    }

}
