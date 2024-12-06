package com.api.v1.dtos.customers;

import com.api.v1.dtos.people.PersonResponseDto;

public record CustomerResponseDto(
        PersonResponseDto personResponseDto
) {
}
