package com.api.v1.services.impl;

import com.api.v1.services.people.PersonModificationService;
import com.api.v1.domain.people.Person;
import com.api.v1.domain.people.PersonAuditTrail;
import com.api.v1.domain.people.PersonAuditTrailRepository;
import com.api.v1.domain.people.PersonRepository;
import com.api.v1.dtos.people.PersonModificationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonModificationServiceImpl implements PersonModificationService {

    @Autowired
    private PersonAuditTrailRepository personAuditTrailRepository;

    @Autowired
    private PersonRepository personRepository;

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
