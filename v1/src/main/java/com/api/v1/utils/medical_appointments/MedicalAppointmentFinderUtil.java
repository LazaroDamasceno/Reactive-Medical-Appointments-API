package com.api.v1.utils.medical_appointments;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.exceptions.medical_appointments.NonExistentMedicalAppointmentException;
import com.api.v1.annotations.MongoDbId;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MedicalAppointmentFinderUtil {

    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentFinderUtil(MedicalAppointmentRepository medicalAppointmentRepository) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    public Mono<MedicalAppointment> find(@MongoDbId String orderNumber) {
        return medicalAppointmentRepository
                .findById(new ObjectId(orderNumber))
                .switchIfEmpty(Mono.error(NonExistentMedicalAppointmentException::new))
                .single();
    }

}
