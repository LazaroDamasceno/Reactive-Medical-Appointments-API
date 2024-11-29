package com.api.v1.medical_appointments.utils;

import com.api.v1.medical_appointments.annotation.OrderNumber;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointments.exceptions.NonExistentMedicalAppointmentException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MedicalAppointmentFinderUtil {

    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public Mono<MedicalAppointment> find(@OrderNumber String orderNumber) {
        return medicalAppointmentRepository
                .findByOrderNumber(new ObjectId(orderNumber))
                .switchIfEmpty(Mono.error(NonExistentMedicalAppointmentException::new))
                .single();
    }

}
