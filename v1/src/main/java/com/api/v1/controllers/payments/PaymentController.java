package com.api.v1.controllers.payments;

import com.api.v1.annotations.MongoDbId;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentPaymentDto;
import com.api.v1.dtos.payment.PaymentResponseDto;
import com.api.v1.services.payments.MedicalAppointmentPaymentService;
import com.api.v1.services.payments.PaymentRetrievalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final MedicalAppointmentPaymentService paymentPaymentService;
    private final PaymentRetrievalService retrievalService;

    public PaymentController(
            MedicalAppointmentPaymentService paymentPaymentService,
            PaymentRetrievalService retrievalService
    ) {
        this.paymentPaymentService = paymentPaymentService;
        this.retrievalService = retrievalService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PaymentResponseDto> payMedicalAppointment(@Valid @RequestBody MedicalAppointmentPaymentDto paymentDto) {
        return paymentPaymentService.payMedicalAppointment(paymentDto);
    }

    @GetMapping("by-payment-number/{paymentNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<PaymentResponseDto> findByPaymentNumber(@MongoDbId @PathVariable String paymentNumber) {
        return retrievalService.findByPaymentNumber(paymentNumber);
    }

    @GetMapping("by-card-number/{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PaymentResponseDto> findAllByCardNumber(@MongoDbId @PathVariable String cardNumber) {
        return retrievalService.findAllByCardNumber(cardNumber);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PaymentResponseDto> findAll() {
        return retrievalService.findAll();
    }
}
