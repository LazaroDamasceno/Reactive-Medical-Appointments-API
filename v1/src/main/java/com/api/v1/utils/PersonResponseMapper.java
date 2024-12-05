package com.api.v1.utils;


import com.api.v1.domain.Person;
import com.api.v1.dtos.PersonResponseDto;

public final class PersonResponseMapper {

    public static PersonResponseDto map(Person person) {
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
