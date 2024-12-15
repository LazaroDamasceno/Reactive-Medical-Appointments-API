package com.api.v1.services.impl;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.services.medical_appointments.MedicalAppointmentCancellationService;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.exceptions.medical_appointments.ImmutableMedicalAppointmentException;
import com.api.v1.utils.medical_appointments.MedicalAppointmentFinderUtil;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentCancellationServiceImpl implements MedicalAppointmentCancellationService {

    private final MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MedicalSlotFinderUtil medicalSlotFinderUtil;
    private final MedicalSlotRepository medicalSlotRepository;

    public MedicalAppointmentCancellationServiceImpl(
            MedicalAppointmentFinderUtil medicalAppointmentFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository,
            MedicalSlotFinderUtil medicalSlotFinderUtil,
            MedicalSlotRepository medicalSlotRepository
    ) {
        this.medicalAppointmentFinderUtil = medicalAppointmentFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.medicalSlotFinderUtil = medicalSlotFinderUtil;
        this.medicalSlotRepository = medicalSlotRepository;
    }

    @Override
    public Mono<Void> cancel(@MongoDbId String id) {
        return medicalAppointmentFinderUtil
                .find(id)
                .flatMap(medicalAppointment -> {
                    return onCanceledMedicalAppointment(medicalAppointment)
                            .then(onCompletedMedicalAppointment(medicalAppointment))
                            .then(Mono.defer(() -> {
                                ObjectId doctorId = medicalAppointment.getDoctor().getId();
                                return medicalSlotFinderUtil
                                        .find(doctorId, medicalAppointment.getBookedAt())
                                        .flatMap(medicalSlot -> {
                                            medicalSlot.setMedicalAppointment(null);
                                            return medicalSlotRepository.save(medicalSlot)
                                                    .then(Mono.defer(() -> {
                                                        medicalAppointment.markAsCanceled();
                                                        return medicalAppointmentRepository.save(medicalAppointment);
                                                    }));
                                        });
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
