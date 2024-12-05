package com.api.v1.medical_appointments;

import com.api.v1.medical_appointments.internal.exceptions.NonExistentMedicalAppointmentException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MedicalAppointmentFinderUtil {

    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentFinderUtil(MedicalAppointmentRepository medicalAppointmentRepository) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    public Mono<MedicalAppointment> find(@OrderNumber String orderNumber) {
        return medicalAppointmentRepository
                .findByOrderNumber(new ObjectId(orderNumber))
                .switchIfEmpty(Mono.error(NonExistentMedicalAppointmentException::new))
                .single();
    }

}
