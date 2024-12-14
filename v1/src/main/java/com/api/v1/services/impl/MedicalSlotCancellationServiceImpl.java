package com.api.v1.services.impl;

import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.medical_slots.MedicalSlot;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.exceptions.medical_slots.ImmutableMedicalSlotException;
import com.api.v1.services.medical_slots.MedicalSlotCancellationService;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalSlotCancellationServiceImpl implements MedicalSlotCancellationService {

    private final MedicalSlotFinderUtil medicalSlotFinderUtil;
    private final MedicalSlotRepository medicalSlotRepository;

    public MedicalSlotCancellationServiceImpl(
            MedicalSlotFinderUtil medicalSlotFinderUtil,
            MedicalSlotRepository medicalSlotRepository
    ) {
        this.medicalSlotFinderUtil = medicalSlotFinderUtil;
        this.medicalSlotRepository = medicalSlotRepository;
    }

    @Override
    public Mono<Void> cancel(@MongoDbId String id) {
        return medicalSlotFinderUtil
                .find(new ObjectId(id))
                .flatMap(medicalSlot -> {
                    return onCanceledMedicalSlot(medicalSlot)
                            .then(onCompletedMedicalSlot(medicalSlot))
                            .then(Mono.defer(() -> {
                                medicalSlot.cancel();
                                return medicalSlotRepository.save(medicalSlot);
                            }));
                })
                .then();
    }

    private Mono<Object> onCanceledMedicalSlot(MedicalSlot medicalSlot) {
        String message = "Medical slot is already canceled.";
        if (medicalSlot.getCanceledAt() != null) {
            return Mono.error(new ImmutableMedicalSlotException(message));
        }
        return Mono.empty();
    }

    private Mono<Object> onCompletedMedicalSlot(MedicalSlot medicalSlot) {
        String message = "Medical slot is already completed.";
        if (medicalSlot.getCompletedAt() != null) {
            return Mono.error(new ImmutableMedicalSlotException(message));
        }
        return Mono.empty();
    }
}
