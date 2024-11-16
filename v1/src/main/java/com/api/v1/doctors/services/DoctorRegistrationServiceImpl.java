package com.api.v1.doctors.services;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.doctors.utils.DoctorResponseMapper;
import com.api.v1.firestore_db.FirestoreCollections;
import com.api.v1.people.services.PersonRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class DoctorRegistrationServiceImpl implements DoctorRegistrationService {

    private final PersonRegistrationService personRegistrationService;

    @Override
    public Mono<DoctorResponseDto> register(@Valid DoctorRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto.personRegistrationDto())
                .flatMap(personId -> {
                    Doctor doctor = Doctor.create(
                            personId,
                            registrationDto.licenseNumber(),
                            registrationDto.speciality()
                    );
                    FirestoreCollections
                            .doctorsCollection()
                            .document()
                            .set(doctor);

                    String doctorId;
                    try {
                        doctorId = FirestoreCollections
                                .doctorsCollection()
                                .whereEqualTo("personId", personId)
                                .get()
                                .get()
                                .getDocuments()
                                .get(0)
                                .getId();
                    } catch (Exception e) {
                        return Mono.empty();
                    }

                    FirestoreCollections
                            .peopleCollection()
                            .document(personId)
                            .update("doctorId", doctorId);

                    try {
                        return DoctorResponseMapper.mapToMono(doctor);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
