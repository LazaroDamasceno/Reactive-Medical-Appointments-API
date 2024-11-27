package com.api.v1.medical_appointment.utils;

import com.api.v1.medical_appointment.annotation.OrderNumber;
import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointment.exceptions.NonExistentMedicalAppointmentException;
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
                .hasElement()
                .flatMap(exists -> {
                   if (!exists) return Mono.error(NonExistentMedicalAppointmentException::new);
                   return medicalAppointmentRepository.findByOrderNumber(new ObjectId(orderNumber));
                });
    }

}
