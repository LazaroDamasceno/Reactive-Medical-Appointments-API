package com.api.v1.services.payments;

import com.api.v1.dtos.payment.PaymentResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRetrievalService {
    Mono<PaymentResponseDto> findByNumber(String paymentNumber);
    Flux<PaymentResponseDto> findAllByCard(String cardNumber);
    Flux<PaymentResponseDto> findAll();
}
