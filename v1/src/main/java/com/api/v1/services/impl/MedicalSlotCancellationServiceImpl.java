package com.api.v1.services.impl;

import com.api.v1.annotations.MongoObjectId;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.exceptions.medical_slots.ImmutableMedicalSlotException;
import com.api.v1.services.medical_slots.MedicalSlotCancellationService;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

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
    public Mono<Void> cancel(@MongoObjectId String id) {
        return medicalSlotFinderUtil
                .find(new ObjectId(id))
                .flatMap(slot -> {
                    AtomicReference<String> message = new AtomicReference<>("Medical slot is already canceled.");
                    if (slot.getCanceledAt() != null) {
                        return Mono.error(new ImmutableMedicalSlotException(message.get()));
                    }
                    message.set("Medical slot has an active medical appointment attached to it.");
                    if (slot.getMedicalAppointment() != null)  {
                        return Mono.error(new ImmutableMedicalSlotException(message.get()));
                    }
                    slot.cancel();
                    return medicalSlotRepository.save(slot);
                })
                .then();
    }
}
