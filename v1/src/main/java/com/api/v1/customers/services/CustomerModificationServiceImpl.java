package com.api.v1.customers.services;

import com.api.v1.customers.domain.CustomerRepository;
import com.api.v1.customers.utils.CustomerFinderUtil;
import com.api.v1.people.annotations.SSN;
import com.api.v1.people.domain.PersonAuditTrail;
import com.api.v1.people.domain.PersonAuditTrailRepository;
import com.api.v1.people.dtos.PersonModificationDto;
import com.api.v1.people.services.PersonModificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerModificationServiceImpl implements CustomerModificationService {

    private final CustomerFinderUtil customerFinderUtil;
    private final CustomerRepository customerRepository;
    private final PersonAuditTrailRepository auditTrailRepository;
    private final PersonModificationService personModificationService;

    @Override
    public Mono<Void> modify(@SSN String ssn, @Valid PersonModificationDto modificationDto) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(foundCustomer -> {
                    PersonAuditTrail auditTrail = PersonAuditTrail.create(foundCustomer.getPerson());
                    return auditTrailRepository
                            .save(auditTrail)
                            .then(Mono.defer(() -> personModificationService
                                    .modify(foundCustomer.getPerson(), modificationDto)
                                    .flatMap(modifiedPerson -> {
                                        foundCustomer.setPerson(modifiedPerson);
                                        return customerRepository.save(foundCustomer);
                            })))
                            .then();
                });

    }

}
