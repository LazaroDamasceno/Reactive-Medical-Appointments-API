package com.api.v1.services.impl;

import com.api.v1.services.PersonModificationService;
import com.api.v1.domain.Person;
import com.api.v1.domain.PersonAuditTrail;
import com.api.v1.domain.PersonAuditTrailRepository;
import com.api.v1.domain.PersonRepository;
import com.api.v1.dtos.PersonModificationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonModificationServiceImpl implements PersonModificationService {

    private final PersonAuditTrailRepository personAuditTrailRepository;
    private final PersonRepository personRepository;

    public PersonModificationServiceImpl(
            PersonAuditTrailRepository personAuditTrailRepository,
            PersonRepository personRepository
    ) {
        this.personAuditTrailRepository = personAuditTrailRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Mono<Person> modify(@NotNull Person person, @Valid PersonModificationDto modificationDto) {
        return personAuditTrailRepository
                .save(PersonAuditTrail.create(person))
                .then(Mono.defer(() -> {
                    person.modify(modificationDto);
                    return personRepository.save(person);
                }));
    }
}
