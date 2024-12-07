package com.api.v1.services.payments;

import com.api.v1.annotations.OrderNumber;
import com.api.v1.domain.payments.PaymentRepository;
import com.api.v1.dtos.payment.PaymentResponseDto;
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
    public Mono<PaymentResponseDto> findByNumber(@OrderNumber String paymentNumber) {
        return paymentFinderUtil
                .find(paymentNumber)
                .flatMap(PaymentResponseMapper::mapToMono);
    }

    @Override
    public Flux<PaymentResponseDto> findAllByCard(@OrderNumber String cardNumber) {
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
