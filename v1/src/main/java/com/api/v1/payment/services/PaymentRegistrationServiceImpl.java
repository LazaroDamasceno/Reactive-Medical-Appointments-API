package com.api.v1.payment.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.payment.domain.Payment;
import com.api.v1.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentRegistrationServiceImpl implements PaymentRegistrationService {

    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> register(Card card, MedicalAppointment medicalAppointment, double price) {
        return Mono.defer(() -> {
            Payment payment = Payment.create(card, medicalAppointment, price);
            return paymentRepository.save(payment);
        });
    }
}
