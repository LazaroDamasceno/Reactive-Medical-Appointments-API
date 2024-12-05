package com.api.v1.payment.controllers;

import com.api.v1.payment.MedicalAppointmentPaymentDto;
import com.api.v1.payment.PaymentResponseDto;
import com.api.v1.payment.MedicalAppointmentPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final MedicalAppointmentPaymentService paymentPaymentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PaymentResponseDto> payMedicalAppointment(@Valid @RequestBody MedicalAppointmentPaymentDto paymentDto) {
        return paymentPaymentService.payMedicalAppointment(paymentDto);
    }
}
