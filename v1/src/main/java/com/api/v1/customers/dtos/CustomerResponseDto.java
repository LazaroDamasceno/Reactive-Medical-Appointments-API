package com.api.v1.customers.dtos;

import com.api.v1.people.domain.Person;

public record CustomerResponseDto(
        Person person,
        String createdAt
) {
}
