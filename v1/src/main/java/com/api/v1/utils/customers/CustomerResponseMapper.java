package com.api.v1.utils.customers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.domain.customers.Customer;
import com.api.v1.utils.people.PersonResponseMapper;

public final class CustomerResponseMapper {

    public static CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

}
