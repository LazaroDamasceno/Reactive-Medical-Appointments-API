package com.api.v1.services.impl;

import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.payments.PaymentRepository;
import com.api.v1.dtos.payment.PaymentResponseDto;
import com.api.v1.services.payments.PaymentRetrievalService;
import com.api.v1.utils.cards.CardFinderUtil;
import com.api.v1.utils.payments.PaymentFinderUtil;
import com.api.v1.utils.payments.PaymentResponseMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentRetrievalServiceImpl implements PaymentRetrievalService {

    private final PaymentRepository paymentRepository;
    private final CardFinderUtil cardFinderUtil;
    private final PaymentFinderUtil paymentFinderUtil;

    public PaymentRetrievalServiceImpl(
            PaymentRepository paymentRepository,
            CardFinderUtil cardFinderUtil,
            PaymentFinderUtil paymentFinderUtil
    ) {
        this.paymentRepository = paymentRepository;
        this.cardFinderUtil = cardFinderUtil;
        this.paymentFinderUtil = paymentFinderUtil;
    }

    @Override
    public Mono<PaymentResponseDto> findByPaymentNumber(@MongoDbId String paymentNumber) {
        return paymentFinderUtil
                .find(paymentNumber)
                .flatMap(PaymentResponseMapper::mapToMono);
    }

    @Override
    public Flux<PaymentResponseDto> findAllByCardNumber(@MongoDbId String cardNumber) {
        return cardFinderUtil
                .findByNumber(cardNumber)
                .flatMapMany(foundCard -> paymentRepository
                        .findAll()
                        .filter(p -> p.card().equals(foundCard))
                        .flatMap(p -> Flux.just(PaymentResponseMapper.mapToDto(p)))
                );
    }

    @Override
    public Flux<PaymentResponseDto> findAll() {
        return paymentRepository
                .findAll()
                .map(PaymentResponseMapper::mapToDto);
    }
}
