package com.api.v1.customers;

import java.time.ZonedDateTime;

public record Customer(
        String personId,
        String createdAt
) {

    public static Customer create(String personId) {
        return new Customer(
                personId,
                ZonedDateTime.now().toString()
        );
    }

}
