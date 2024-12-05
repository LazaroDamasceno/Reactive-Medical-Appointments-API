package com.api.v1.medical_appointments.services;

import com.api.v1.medical_appointments.MedicalAppointmentCompletionService;
import com.api.v1.medical_appointments.OrderNumber;
import com.api.v1.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.medical_appointments.exceptions.ImmutableMedicalAppointmentException;
import com.api.v1.medical_appointments.MedicalAppointmentFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentCompletionServiceImpl implements MedicalAppointmentCompletionService {

    private final MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentCompletionServiceImpl(
            MedicalAppointmentFinderUtil medicalAppointmentFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository
    ) {
        this.medicalAppointmentFinderUtil = medicalAppointmentFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Mono<Void> complete(@OrderNumber String orderNumber) {
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
