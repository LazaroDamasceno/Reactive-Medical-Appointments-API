package com.api.v1.customers.utils;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.people.utils.PersonFinderUtil;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class CustomerResponseMapper {

    public CustomerResponseDto mapToDto(Customer customer) throws Exception {
        return new CustomerResponseDto(
                PersonFinderUtil.findById(customer.personId()),
                customer.createdAt()
        );
    }

    public Mono<CustomerResponseDto> mapToMono(Customer customer) throws Exception {
        return Mono.just(mapToDto(customer));
    }

}
