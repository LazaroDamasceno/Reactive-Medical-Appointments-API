package com.api.v1.people;

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
