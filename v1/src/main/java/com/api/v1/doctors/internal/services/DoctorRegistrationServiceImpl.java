package com.api.v1.doctors.internal.services;

import com.api.v1.doctors.DoctorRegistrationService;
import com.api.v1.doctors.Doctor;
import com.api.v1.doctors.DoctorRepository;
import com.api.v1.doctors.DoctorRegistrationDto;
import com.api.v1.doctors.DoctorResponseDto;
import com.api.v1.doctors.internal.exceptions.DuplicatedMedicalLicenseNumberException;
import com.api.v1.doctors.DoctorResponseMapper;
import com.api.v1.people.internal.services.PersonRegistrationService;
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
