package com.api.v1.payment.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.payment.domain.Payment;
import reactor.core.publisher.Mono;

public interface PaymentRegistrationService {
    Mono<Payment> register(Card card, MedicalAppointment medicalAppointment, double price);
}
