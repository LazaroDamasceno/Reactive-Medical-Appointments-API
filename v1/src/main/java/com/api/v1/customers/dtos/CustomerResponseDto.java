package com.api.v1.customers.dtos;

import com.api.v1.people.dtos.PersonResponseDto;

public record CustomerResponseDto(
        PersonResponseDto personResponseDto
) {
}
