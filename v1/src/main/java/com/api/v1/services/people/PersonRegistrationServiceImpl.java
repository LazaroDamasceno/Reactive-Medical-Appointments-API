package com.api.v1.services.people;

import com.api.v1.domain.people.Person;
import com.api.v1.domain.people.PersonRepository;
import com.api.v1.dtos.people.PersonRegistrationDto;
import com.api.v1.exceptions.people.DuplicatedEmailException;
import com.api.v1.exceptions.people.DuplicatedSsnException;
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
