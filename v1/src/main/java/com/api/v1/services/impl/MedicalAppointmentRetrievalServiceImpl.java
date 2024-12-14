package com.api.v1.services.impl;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.annotations.SSN;
import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.services.medical_appointments.MedicalAppointmentRetrievalService;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.utils.medical_appointments.MedicalAppointmentFinderUtil;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentRetrievalServiceImpl implements MedicalAppointmentRetrievalService {

    @Autowired
    private MedicalAppointmentFinderUtil medicalAppointmentFinderUtil;

    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private DoctorFinderUtil doctorFinderUtil;

    @Override
    public Mono<MedicalAppointmentResponseDto> findByid(@MongoDbId String id) {
        return medicalAppointmentFinderUtil
                .find(id)
                .flatMap(MedicalAppointmentResponseMapper::mapToMono);
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAll() {
        return medicalAppointmentRepository
                .findAll()
                .map(MedicalAppointmentResponseMapper::mapToDto);
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByCustomer(@SSN String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMapMany(foundCustomer -> medicalAppointmentRepository
                            .findAll()
                            .filter(ma-> ma.getCustomer().equals(foundCustomer))
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMapMany(foundDoctor -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getDoctor().equals(foundDoctor))
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByCustomerAndDoctor(
            @SSN String ssn,
            @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        Mono<Customer> customerMono = customerFinderUtil.find(ssn);
        Mono<Doctor> doctorMono = doctorFinderUtil.find(medicalLicenseNumber);
        return Mono.zip(customerMono, doctorMono)
                .flatMapMany(tuple -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma -> ma.getCustomer().equals(tuple.getT1())
                                && ma.getDoctor().equals(tuple.getT2())
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveCustomer(@SSN String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMapMany(foundCustomer -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getCustomer().equals(foundCustomer)
                                && ma.getCanceledAt() == null
                                && ma.getDoctor() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMapMany(foundDoctor -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getDoctor().equals(foundDoctor)
                                && ma.getCanceledAt() == null
                                && ma.getDoctor() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByCustomerAndDoctor(
            @SSN String ssn,
            @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        Mono<Customer> customerMono = customerFinderUtil.find(ssn);
        Mono<Doctor> doctorMono = doctorFinderUtil.find(medicalLicenseNumber);
        return Mono.zip(customerMono, doctorMono)
                .flatMapMany(tuple -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma -> ma.getCustomer().equals(tuple.getT1())
                                && ma.getDoctor().equals(tuple.getT2())
                                && ma.getCompletedAt() == null
                                && ma.getCanceledAt() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCompletedCustomer(@SSN String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMapMany(foundCustomer -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getCustomer().equals(foundCustomer)
                                && ma.getCompletedAt() != null
                                && ma.getCanceledAt() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCompletedByDoctor(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMapMany(foundDoctor -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getDoctor().equals(foundDoctor)
                                && ma.getCompletedAt() != null
                                && ma.getCanceledAt() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCompletedByCustomerAndDoctor(
            @SSN String ssn,
            @MedicalLicenseNumber String medicalLicenseNumber
    ) {
        Mono<Customer> customerMono = customerFinderUtil.find(ssn);
        Mono<Doctor> doctorMono = doctorFinderUtil.find(medicalLicenseNumber);
        return Mono.zip(customerMono, doctorMono)
                .flatMapMany(tuple -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma -> ma.getCustomer().equals(tuple.getT1())
                                && ma.getDoctor().equals(tuple.getT2())
                                && ma.getCompletedAt() != null
                                && ma.getCanceledAt() == null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCanceledCustomer(@SSN String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMapMany(foundCustomer -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getCustomer().equals(foundCustomer)
                                && ma.getCompletedAt() == null
                                && ma.getCanceledAt() != null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCanceledByDoctor(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMapMany(foundDoctor -> medicalAppointmentRepository
                        .findAll()
                        .filter(ma-> ma.getDoctor().equals(foundDoctor)
                                && ma.getCompletedAt() == null
                                && ma.getCanceledAt() != null
                        )
                )
                .flatMap(ma -> Flux.just(MedicalAppointmentResponseMapper.mapToDto(ma)));
    }
}
