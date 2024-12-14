package com.api.v1.services.impl;

import com.api.v1.services.payments.MedicalAppointmentPaymentService;
import com.api.v1.utils.cards.CardFinderUtil;
import com.api.v1.utils.medical_appointments.MedicalAppointmentFinderUtil;
import com.api.v1.domain.payments.Payment;
import com.api.v1.domain.payments.PaymentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentPaymentDto;
import com.api.v1.dtos.payment.PaymentResponseDto;
import com.api.v1.exceptions.payments.NotAllowedPaymentException;
import com.api.v1.utils.payments.PaymentResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentPaymentServiceImpl implements MedicalAppointmentPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CardFinderUtil cardFinderUtil;

    @Autowired
    private MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;

    @Override
    public Mono<PaymentResponseDto> payMedicalAppointment(@Valid MedicalAppointmentPaymentDto paymentDto) {
        var cardMono = cardFinderUtil.findByNumber(paymentDto.cardNumber());
        var appointmentMono = medicalAppointmentFinderUtil.find(paymentDto.appointmentid());
        return cardMono
                .zipWith(appointmentMono)
                .flatMap(tuple -> {
                    if (tuple.getT2().getCompletedAt() == null) {
                        return Mono.error(NotAllowedPaymentException::new);
                    }
                    Payment payment = Payment.create(tuple.getT1(), tuple.getT2(), paymentDto.price());
                    return paymentRepository.save(payment);
                })
                .flatMap(PaymentResponseMapper::mapToMono);
    }
}
