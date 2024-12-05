package com.api.v1.customers;

import com.api.v1.people.PersonResponseDto;

public record CustomerResponseDto(
        PersonResponseDto personResponseDto
) {
}
