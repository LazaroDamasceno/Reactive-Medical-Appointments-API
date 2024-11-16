package com.api.v1.people.services;

import com.api.v1.people.domain.Person;
import com.api.v1.people.domain.PersonRepository;
import com.api.v1.people.dtos.PersonModificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonModificationServiceImpl implements PersonModificationService {

    private final PersonRepository personRepository;

    @Override
    public Mono<Void> modify(Person person, PersonModificationDto modificationDto) {
        return Mono.defer(() -> {
            person.modify(modificationDto);
            return personRepository.save(person);
        }).then();
    }

}
