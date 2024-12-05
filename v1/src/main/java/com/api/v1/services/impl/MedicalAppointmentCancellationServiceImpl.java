package com.api.v1.services.impl;

import com.api.v1.services.MedicalAppointmentCancellationService;
import com.api.v1.annotations.OrderNumber;
import com.api.v1.domain.MedicalAppointmentRepository;
import com.api.v1.exceptions.ImmutableMedicalAppointmentException;
import com.api.v1.utils.MedicalAppointmentFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentCancellationServiceImpl implements MedicalAppointmentCancellationService {

    private final MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentCancellationServiceImpl(
            MedicalAppointmentFinderUtil medicalAppointmentFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository
    ) {
        this.medicalAppointmentFinderUtil = medicalAppointmentFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Mono<Void> cancel(@OrderNumber String orderNumber) {
        return medicalAppointmentFinderUtil
                .find(orderNumber)
                .flatMap(medicalAppointment -> {
                    if (medicalAppointment.getCanceledAt() != null && medicalAppointment.getCompletedAt() == null) {
                        String message = "The sought medical appointment is already canceled.";
                        return Mono.error(new ImmutableMedicalAppointmentException(message));
                    }
                    if (medicalAppointment.getCanceledAt() == null && medicalAppointment.getCompletedAt() != null) {
                        String message = "The sought medical appointment is already completed.";
                        return Mono.error(new ImmutableMedicalAppointmentException(message));
                    }
                    medicalAppointment.markAsCanceled();
                    return medicalAppointmentRepository.save(medicalAppointment);
                })
                .then();
    }

}
