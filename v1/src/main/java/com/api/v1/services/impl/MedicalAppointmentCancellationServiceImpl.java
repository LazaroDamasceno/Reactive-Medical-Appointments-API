package com.api.v1.services.impl;

import com.api.v1.services.medical_appointments.MedicalAppointmentCancellationService;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.exceptions.medical_appointments.ImmutableMedicalAppointmentException;
import com.api.v1.utils.medical_appointments.MedicalAppointmentFinderUtil;
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
    public Mono<Void> cancel(@MongoDbId String id) {
        return medicalAppointmentFinderUtil
                .find(id)
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
