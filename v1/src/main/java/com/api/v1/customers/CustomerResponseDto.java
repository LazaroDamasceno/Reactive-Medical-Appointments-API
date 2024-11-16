package com.api.v1.customers;

import com.api.v1.people.domain.Person;

public record CustomerResponseDto(
        Person person,
        String createdAt
) {
}
