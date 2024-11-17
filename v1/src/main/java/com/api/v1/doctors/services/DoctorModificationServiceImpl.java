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

    private final DoctorRepository doctorRepository;
    private final DoctorFinderUtil doctorFinderUtil;
    private final PersonModificationService personModificationService;

    @Override
    public Mono<Void> modify(@Valid DoctorModificationDto modificationDto) {
        return doctorFinderUtil
                .find(modificationDto.licenseNumberDto())
                .flatMap(foundDoctor -> personModificationService
                        .modify(foundDoctor.getPerson(), modificationDto.modificationDto())
                        .flatMap(foundPerson -> {
                           foundDoctor.setPerson(foundPerson);
                           return doctorRepository.save(foundDoctor);
                        })
                ).then();
    }

}
