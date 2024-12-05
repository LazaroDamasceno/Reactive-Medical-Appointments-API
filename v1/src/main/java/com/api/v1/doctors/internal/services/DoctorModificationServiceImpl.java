package com.api.v1.doctors.internal.services;

import com.api.v1.doctors.DoctorModificationService;
import com.api.v1.doctors.MedicalLicenseNumber;
import com.api.v1.doctors.DoctorRepository;
import com.api.v1.doctors.DoctorFinderUtil;
import com.api.v1.people.PersonModificationDto;
import com.api.v1.people.PersonModificationService;
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
