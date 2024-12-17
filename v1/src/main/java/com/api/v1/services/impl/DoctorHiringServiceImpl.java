package com.api.v1.services.impl;

import com.api.v1.services.doctors.DoctorHiringService;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.dtos.doctors.DoctorHiringDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.exceptions.medical_appointments.DuplicatedMedicalLicenseNumberException;
import com.api.v1.utils.doctors.DoctorResponseMapper;
import com.api.v1.services.people.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorHiringServiceImpl implements DoctorHiringService {

    private final DoctorRepository doctorRepository;
    private final PersonRegistrationService personRegistrationService;

    public DoctorHiringServiceImpl(
            DoctorRepository doctorRepository,
            PersonRegistrationService personRegistrationService) {
        this.doctorRepository = doctorRepository;
        this.personRegistrationService = personRegistrationService;
    }

    @Override
    public Mono<DoctorResponseDto> hire(@Valid DoctorHiringDto registrationDto) {
        return personRegistrationService
                .register(registrationDto.personRegistrationDto())
                .flatMap(foundPerson -> doctorRepository
                        .findByLicenseNumber(registrationDto.licenseNumber())
                        .singleOptional()
                        .flatMap(optional -> {
                            if (optional.isPresent()) {
                                return Mono.error(DuplicatedMedicalLicenseNumberException::new);
                            }
                            return Mono.defer(() -> {
                               Doctor doctor = Doctor.create(registrationDto.licenseNumber(), foundPerson);
                               return doctorRepository.save(doctor);
                            });
                }))
                .flatMap(savedDoctor -> Mono.just(DoctorResponseMapper.mapToDto(savedDoctor)));
    }
}
