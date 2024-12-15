package com.api.v1.services.impl;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.services.medical_appointments.MedicalAppointmentCompletionService;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.exceptions.medical_appointments.ImmutableMedicalAppointmentException;
import com.api.v1.services.medical_slots.MedicalSlotCompletionService;
import com.api.v1.utils.medical_appointments.MedicalAppointmentFinderUtil;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentCompletionServiceImpl implements MedicalAppointmentCompletionService {

    private final MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MedicalSlotCompletionService medicalSlotCompletionService;
    private final MedicalSlotFinderUtil medicalSlotFinderUtil;

    public MedicalAppointmentCompletionServiceImpl(
            MedicalAppointmentFinderUtil medicalAppointmentFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository,
            MedicalSlotCompletionService medicalSlotCompletionService,
            MedicalSlotFinderUtil medicalSlotFinderUtil
    ) {
        this.medicalAppointmentFinderUtil = medicalAppointmentFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.medicalSlotCompletionService = medicalSlotCompletionService;
        this.medicalSlotFinderUtil = medicalSlotFinderUtil;
    }

    @Override
    public Mono<Void> complete(@MongoDbId String id) {
        return medicalAppointmentFinderUtil
                .find(id)
                .flatMap(medicalAppointment -> {
                    return onCanceledMedicalAppointment(medicalAppointment)
                            .then(onCompletedMedicalAppointment(medicalAppointment))
                            .then(Mono.defer(() -> {
                                medicalAppointment.markAsCompleted();
                                return medicalAppointmentRepository
                                        .save(medicalAppointment)
                                        .then(Mono.defer(() -> {
                                            ObjectId doctorId = medicalAppointment.getDoctor().getId();
                                            return medicalSlotFinderUtil
                                                    .find(doctorId, medicalAppointment.getBookedAt())
                                                    .flatMap(medicalSlot -> {
                                                        return medicalSlotCompletionService.complete(medicalAppointment.getId());
                                                    });
                                        }));
                            }));
                })
                .then();
    }

    private Mono<Void> onCanceledMedicalAppointment(MedicalAppointment medicalAppointment) {
        if (medicalAppointment.getCanceledAt() != null && medicalAppointment.getCompletedAt() == null) {
            String message = "The sought medical appointment is already canceled.";
            return Mono.error(new ImmutableMedicalAppointmentException(message));
        }
        return Mono.empty();
    }

    private Mono<Void> onCompletedMedicalAppointment(MedicalAppointment medicalAppointment) {
        if (medicalAppointment.getCanceledAt() == null && medicalAppointment.getCompletedAt() != null) {
            String message = "The sought medical appointment is already completed.";
            return Mono.error(new ImmutableMedicalAppointmentException(message));
        }
        return Mono.empty();
    }
}
