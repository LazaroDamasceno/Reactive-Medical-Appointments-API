package com.api.v1.utils;

import com.api.v1.dtos.CustomerResponseDto;
import com.api.v1.domain.Customer;

public final class CustomerResponseMapper {

    public static CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

}
