package com.api.v1.services.impl;

import com.api.v1.services.DoctorRegistrationService;
import com.api.v1.domain.Doctor;
import com.api.v1.domain.DoctorRepository;
import com.api.v1.dtos.DoctorRegistrationDto;
import com.api.v1.dtos.DoctorResponseDto;
import com.api.v1.exceptions.DuplicatedMedicalLicenseNumberException;
import com.api.v1.utils.DoctorResponseMapper;
import com.api.v1.services.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {

    private final DoctorRepository doctorRepository;
    private final PersonRegistrationService personRegistrationService;

    public DoctorRegistrationServiceImpl(
            DoctorRepository doctorRepository,
            PersonRegistrationService personRegistrationService) {
        this.doctorRepository = doctorRepository;
        this.personRegistrationService = personRegistrationService;
    }

    @Override
    public Mono<DoctorResponseDto> register(@Valid DoctorRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto.personRegistrationDto())
                .flatMap(foundPerson -> doctorRepository
                        .findByLicenseNumber(registrationDto.licenseNumberDto())
                        .singleOptional()
                        .flatMap(optional -> {
                            if (optional.isPresent()) {
                                return Mono.error(DuplicatedMedicalLicenseNumberException::new);
                            }
                            return Mono.defer(() -> {
                               Doctor doctor = Doctor.create(registrationDto.licenseNumberDto(), foundPerson);
                               return doctorRepository.save(doctor);
                            });
                }))
                .flatMap(savedDoctor -> Mono.just(DoctorResponseMapper.map(savedDoctor)));
    }
}
