package com.api.v1.doctors.services;

import com.api.v1.doctors.domain.DoctorRepository;
import com.api.v1.doctors.dtos.DoctorModificationDto;
import com.api.v1.doctors.utils.DoctorFinderUtil;
import com.api.v1.people.services.PersonModificationService;
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
    public Mono<Void> modify(@Valid DoctorModificationDto modificationDto) {
        return doctorFinderUtil
                .find(modificationDto.licenseNumberDto())
                .flatMap(doctor -> personModificationService
                        .modify(doctor.getPerson(), modificationDto.modificationDto())
                        .flatMap(modifiedPerson -> {
                            doctor.setPerson(modifiedPerson);
                            return doctorRepository.save(doctor);
                        })
                ).then();
    }

}
