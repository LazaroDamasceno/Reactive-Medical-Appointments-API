package com.api.v1.doctors.services;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.domain.DoctorRepository;
import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.doctors.utils.DoctorResponseMapper;
import com.api.v1.people.services.PersonRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {

    private final DoctorRepository doctorRepository;
    private final PersonRegistrationService personRegistrationService;

    @Override
    public Mono<DoctorResponseDto> register(@Valid DoctorRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto.personRegistrationDto())
                .flatMap(foundPerson -> {
                    Doctor doctor = Doctor.create(registrationDto.licenseNumber(), foundPerson);
                    return doctorRepository.save(doctor);
                })
                .flatMap(savedDoctor -> Mono.just(DoctorResponseMapper.map(savedDoctor)));
    }
}
