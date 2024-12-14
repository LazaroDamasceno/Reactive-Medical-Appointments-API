package com.api.v1.utils.medical_slots;

import com.api.v1.domain.medical_slots.MedicalSlot;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.exceptions.medical_slots.UnavailableMedicalSlotException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MedicalSlotFinderUtil {

    @Autowired
    private MedicalSlotRepository medicalSlotRepository;

    public Mono<MedicalSlot> find(ObjectId id) {
        String message = "No medical slot was found under the given id.";
        return medicalSlotRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new UnavailableMedicalSlotException(message)));
    }

    public Mono<MedicalSlot> find(ObjectId doctorId, String availableAt) {
        String message = "No medical slot was found.";
        return medicalSlotRepository
                .findAll()
                .filter(slot ->
                        slot.getDoctor().getId().equals(doctorId)
                        && slot.getAvailableAt().equals(availableAt)
                )
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new UnavailableMedicalSlotException(message)));
    }
}
