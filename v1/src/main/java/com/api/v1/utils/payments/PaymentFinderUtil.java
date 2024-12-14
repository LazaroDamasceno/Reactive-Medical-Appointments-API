package com.api.v1.utils.payments;

import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.payments.Payment;
import com.api.v1.domain.payments.PaymentRepository;
import com.api.v1.exceptions.payments.NonExistentPaymentException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PaymentFinderUtil {

    @Autowired
    private PaymentRepository paymentRepository;

    public Mono<Payment> find(@MongoDbId String paymentNumber) {
        return paymentRepository
                .findAll()
                .filter(p -> p.id().equals(new ObjectId(paymentNumber)))
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(NonExistentPaymentException::new));
    }
}
