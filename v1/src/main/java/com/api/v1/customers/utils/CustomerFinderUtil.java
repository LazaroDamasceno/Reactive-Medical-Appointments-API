package com.api.v1.customers.utils;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.exceptions.CustomerNotFoundException;
import com.api.v1.firestore_db.FirestoreCollections;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class CustomerFinderUtil {

    public Mono<Customer> find(String ssn) {
        return Mono.defer(() -> {
            try {
                var queriedPerson = FirestoreCollections
                        .peopleCollection()
                        .whereEqualTo("ssn", ssn)
                        .get()
                        .get();
                if (queriedPerson.isEmpty()) {
                    return Mono.error(new CustomerNotFoundException(ssn));
                }
                var personId = queriedPerson
                        .getDocuments()
                        .get(0)
                        .getId();
                var foundCustomer = FirestoreCollections
                        .customersCollection()
                        .whereEqualTo("personId", personId)
                        .get()
                        .get()
                        .getDocuments()
                        .get(0)
                        .toObject(Customer.class);
                return Mono.just(foundCustomer);
            } catch (Exception e) {
                return Mono.empty();
            }
        });
    }

}
