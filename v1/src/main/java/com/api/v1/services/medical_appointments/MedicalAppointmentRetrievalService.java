package com.api.v1.services.medical_appointments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentRetrievalService {
    Mono<MedicalAppointmentResponseDto> findById(String id);
    Flux<MedicalAppointmentResponseDto> findAll();
    Flux<MedicalAppointmentResponseDto> findAllByCustomer(String ssn);
    Flux<MedicalAppointmentResponseDto> findAllByDoctor(String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findAllByCustomerAndDoctor(String ssn, String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findActivatedByCustomer(String ssn);
    Flux<MedicalAppointmentResponseDto> findActivatedByDoctor(String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findActivatedByCustomerAndDoctor(String ssn, String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findCompletedByCustomer(String ssn);
    Flux<MedicalAppointmentResponseDto> findCompletedByDoctor(String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findCompletedByCustomerAndDoctor(String ssn, String medicalLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findCanceledByCustomer(String ssn);
    Flux<MedicalAppointmentResponseDto> findCanceledByDoctor(String medicalLicenseNumber);
}
