package com.api.v1.customers.dtos;

import com.api.v1.people.dtos.PersonResponseDto;

import java.util.Date;

public record CustomerResponseDto(
        PersonResponseDto personResponseDto
) {
}
