package com.api.v1.customers;

import com.api.v1.people.PersonResponseMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerResponseMapper {

    public CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

}
