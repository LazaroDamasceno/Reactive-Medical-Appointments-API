package com.api.v1.doctors.utils;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.exceptions.DoctorNotFoundException;
import com.api.v1.firestore_db.FirestoreCollections;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class DoctorFinderUtil {

    public Mono<Doctor> find(String licenseNumber) {
        return Mono.defer(() -> {
            try {
                var queriedPerson = FirestoreCollections
                        .doctorsCollection()
                        .whereEqualTo("licenseNumber", licenseNumber)
                        .get()
                        .get();
                if (queriedPerson.isEmpty()) {
                    return Mono.error(new DoctorNotFoundException(licenseNumber));
                }
                Doctor foundPerson = queriedPerson
                        .getDocuments()
                        .get(0)
                        .toObject(Doctor.class);
                return Mono.just(foundPerson);
            } catch (Exception ignored) {
                return Mono.empty();
            }
        });
    }

}
