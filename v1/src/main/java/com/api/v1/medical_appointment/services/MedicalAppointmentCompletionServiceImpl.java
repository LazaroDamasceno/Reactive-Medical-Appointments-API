package com.api.v1.medical_appointment.services;

import com.api.v1.medical_appointment.annotation.OrderNumber;
import com.api.v1.medical_appointment.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointment.exceptions.ImmutableMedicalAppointmentException;
import com.api.v1.medical_appointment.utils.MedicalAppointmentFinderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MedicalAppointmentCompletionServiceImpl implements MedicalAppointmentCancellationService {

    private final MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    @Override
    public Mono<Void> cancel(@OrderNumber String orderNumber) {
        return medicalAppointmentFinderUtil
                .find(orderNumber)
                .flatMap(medicalAppointment -> {
                    if (medicalAppointment.getCanceledAt() != null && medicalAppointment.getCompletedAt() == null) {
                        String message = "The sought medical appointment is already canceled.";
                        return Mono.error(new ImmutableMedicalAppointmentException(message));
                    }
                    if (medicalAppointment.getCanceledAt() == null && medicalAppointment.getCompletedAt() != null) {
                        String message = "The sought medical appointment is already completed.";
                        return Mono.error(new ImmutableMedicalAppointmentException(message));
                    }
                    medicalAppointment.markAsCompleted();
                    return medicalAppointmentRepository.save(medicalAppointment);
                })
                .then();
    }

}
