package com.api.v1.people.services;

import com.api.v1.people.domain.Person;
import com.api.v1.people.domain.PersonAuditTrail;
import com.api.v1.people.domain.PersonAuditTrailRepository;
import com.api.v1.people.domain.PersonRepository;
import com.api.v1.people.dtos.PersonModificationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonModificationServiceImpl implements PersonModificationService {

    private final PersonAuditTrailRepository personAuditTrailRepository;
    private final PersonRepository personRepository;

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
