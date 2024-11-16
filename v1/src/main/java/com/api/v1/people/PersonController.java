package com.api.v1.people;

import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonRegistrationDto;
import com.api.v1.people.services.PersonRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRegistrationService registrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<String> register(@Valid @RequestBody PersonRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

}
