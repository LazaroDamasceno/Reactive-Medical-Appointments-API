package com.api.v1.utils.customers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.domain.customers.Customer;
import com.api.v1.utils.people.PersonResponseMapper;
import reactor.core.publisher.Mono;

public final class CustomerResponseMapper {

    public static CustomerResponseDto mapToDto(Customer customer) {
        return new CustomerResponseDto(
                PersonResponseMapper.map(customer.getPerson())
        );
    }

    public static Mono<CustomerResponseDto> mapToMono(Customer customer) {
        return Mono.just(mapToDto(customer));
    }

}
