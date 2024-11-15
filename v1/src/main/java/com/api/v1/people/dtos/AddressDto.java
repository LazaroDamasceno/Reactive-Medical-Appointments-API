package com.api.v1.people.dtos;

public record AddressDto(
        String state,
        String city,
        String street,
        String zipcode
) {
}
