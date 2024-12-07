package com.api.v1.services.payments;

import com.api.v1.dtos.payment.PaymentResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRetrievalService {
    Mono<PaymentResponseDto> findByPaymentNumber(String paymentNumber);
    Flux<PaymentResponseDto> findAllByCardNumber(String cardNumber);
    Flux<PaymentResponseDto> findAll();
}
