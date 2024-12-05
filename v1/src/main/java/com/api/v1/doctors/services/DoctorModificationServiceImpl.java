package com.api.v1.doctors.services;

import com.api.v1.doctors.DoctorModificationService;
import com.api.v1.doctors.annotations.MedicalLicenseNumber;
import com.api.v1.doctors.DoctorRepository;
import com.api.v1.doctors.DoctorFinderUtil;
import com.api.v1.people.PersonModificationDto;
import com.api.v1.people.PersonModificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorModificationServiceImpl implements DoctorModificationService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final PersonModificationService personModificationService;
    private final DoctorRepository doctorRepository;

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
