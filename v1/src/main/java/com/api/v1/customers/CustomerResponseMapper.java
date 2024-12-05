package com.api.v1.customers;

import com.api.v1.people.PersonResponseMapper;

public final class CustomerResponseMapper {

    public static CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

}
