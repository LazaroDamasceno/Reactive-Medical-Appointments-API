package com.api.v1.people.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressDto(
        @NotBlank @Size(min = 2, max = 2) String state,
        @NotBlank String city,
        @NotBlank String street,
        @NotBlank @Size(min = 5, max = 5) String zipcode
) {
}
