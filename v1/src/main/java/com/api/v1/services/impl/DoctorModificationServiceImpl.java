package com.api.v1.services.impl;

import com.api.v1.services.doctors.DoctorModificationService;
import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.services.people.PersonModificationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorModificationServiceImpl implements DoctorModificationService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final PersonModificationService personModificationService;
    private final DoctorRepository doctorRepository;

    public DoctorModificationServiceImpl(
            DoctorFinderUtil doctorFinderUtil,
            PersonModificationService personModificationService,
            DoctorRepository doctorRepository
    ) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.personModificationService = personModificationService;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Mono<Void> modify(@MedicalLicenseNumber String medicalLicenseNumber, @Valid PersonModificationDto modificationDto) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMap(doctor -> personModificationService
                        .modify(doctor.getPerson(), modificationDto)
                            .flatMap(modifiedPerson -> {
                                doctor.setPerson(modifiedPerson);
                                return doctorRepository.save(doctor);
                            })
                ).then();
    }
}
