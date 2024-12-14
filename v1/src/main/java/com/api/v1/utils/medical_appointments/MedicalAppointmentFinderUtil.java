package com.api.v1.utils.medical_appointments;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.exceptions.medical_appointments.NonExistentMedicalAppointmentException;
import com.api.v1.annotations.MongoDbId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MedicalAppointmentFinderUtil {

    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

    public Mono<MedicalAppointment> find(@MongoDbId String id) {
        return medicalAppointmentRepository
                .findById(new ObjectId(id))
                .switchIfEmpty(Mono.error(NonExistentMedicalAppointmentException::new))
                .single();
    }

}
