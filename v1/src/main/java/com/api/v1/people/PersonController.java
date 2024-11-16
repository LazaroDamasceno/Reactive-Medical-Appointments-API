package com.api.v1.people;

import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonRegistrationDto;
import com.api.v1.people.services.PersonRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRegistrationService registrationService;

    @PostMapping
    public Mono<Person> register(@Valid @RequestBody PersonRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

}