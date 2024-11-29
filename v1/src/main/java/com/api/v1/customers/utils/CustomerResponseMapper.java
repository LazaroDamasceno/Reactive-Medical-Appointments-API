package com.api.v1.customers.utils;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.people.utils.PersonResponseMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerResponseMapper {

    public CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

}
