package com.api.v1.services.impl;

import com.api.v1.domain.doctors.DoctorAuditTrail;
import com.api.v1.domain.doctors.DoctorAuditTrailRepository;
import com.api.v1.services.doctors.DoctorRegistrationService;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.dtos.doctors.DoctorRegistrationDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.exceptions.medical_appointments.DuplicatedMedicalLicenseNumberException;
import com.api.v1.utils.doctors.DoctorResponseMapper;
import com.api.v1.services.people.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DoctorRegistrationServiceImpl implements DoctorRegistrationService {

    private final DoctorRepository doctorRepository;
    private final PersonRegistrationService personRegistrationService;
    private final DoctorAuditTrailRepository doctorAuditTrailRepository;

    public DoctorRegistrationServiceImpl(
            DoctorRepository doctorRepository,
            PersonRegistrationService personRegistrationService,
            DoctorAuditTrailRepository doctorAuditTrailRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.personRegistrationService = personRegistrationService;
        this.doctorAuditTrailRepository = doctorAuditTrailRepository;
    }

    @Override
    public Mono<DoctorResponseDto> register(@Valid DoctorRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto.personRegistrationDto())
                .flatMap(foundPerson -> doctorRepository
                        .findByLicenseNumber(registrationDto.licenseNumberDto())
                        .singleOptional()
                        .flatMap(optional -> {
                            return handleDuplicatedMedicalLicenseNumber(optional)
                                    .then(handleTerminatedDoctor(registrationDto.licenseNumberDto()))
                                    .then(Mono.defer(() -> {
                                        Doctor doctor = Doctor.create(registrationDto.licenseNumberDto(), foundPerson);
                                        return doctorRepository.save(doctor);
                                    }));
                }))
                .flatMap(savedDoctor -> Mono.just(DoctorResponseMapper.mapToDto(savedDoctor)));
    }

    private Mono<Void> handleDuplicatedMedicalLicenseNumber(Optional<Doctor> optional) {
        if (optional.isPresent() && optional.get().getTerminatedAt() == null) {
            return Mono.error(DuplicatedMedicalLicenseNumberException::new);
        }
        return Mono.empty();
    }

    public Mono<Void> handleTerminatedDoctor(String medicalLicenseNumber) {
        return doctorRepository
                .findAll()
                .filter(doctor ->
                    doctor.getTerminatedAt() != null
                    && doctor.getLicenseNumber().equals(medicalLicenseNumber)
                )
                .singleOrEmpty()
                .switchIfEmpty(Mono.empty())
                .flatMap(doctor -> {
                    DoctorAuditTrail doctorAuditTrail = DoctorAuditTrail.create(doctor);
                    return doctorAuditTrailRepository
                            .save(doctorAuditTrail)
                            .then(Mono.defer(() -> {
                               return doctorRepository.delete(doctor);
                            }));
                });
    }
}
