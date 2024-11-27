package com.api.v1.people.utils;

import com.api.v1.people.domain.Person;
import com.api.v1.people.dtos.PersonResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonResponseMapper {

    public PersonResponseDto map(Person person) {
        return new PersonResponseDto(
                person.getFullName(),
                person.getBirthDate(),
                person.getSsn(),
                person.getEmail(),
                person.getAddress(),
                person.getPhoneNumber(),
                person.getGender()
        );
    }

}
