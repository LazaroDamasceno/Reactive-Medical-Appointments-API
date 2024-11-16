package com.api.v1.people.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank String state,
        @NotBlank String city,
        @NotBlank String street,
        @NotBlank String zipcode
) {
}
