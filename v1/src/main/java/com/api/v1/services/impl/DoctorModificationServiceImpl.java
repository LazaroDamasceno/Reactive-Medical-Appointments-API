package com.api.v1.services.impl;

import com.api.v1.services.doctors.DoctorModificationService;
import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.services.people.PersonModificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorModificationServiceImpl implements DoctorModificationService {

    @Autowired
    private DoctorFinderUtil doctorFinderUtil;

    @Autowired
    private PersonModificationService personModificationService;

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Mono<Void> modify(@MedicalLicenseNumber String medicalLicenseNumber, @Valid PersonModificationDto modificationDto) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMap(doctor -> personModificationService
                        .modify(doctor.getPerson(), modificationDto)
                            .flatMap(modifiedPerson -> {
                                doctor.setPerson(modifiedPerson);
                                return doctorRepository.save(doctor);
                            })
                ).then();
    }
}
