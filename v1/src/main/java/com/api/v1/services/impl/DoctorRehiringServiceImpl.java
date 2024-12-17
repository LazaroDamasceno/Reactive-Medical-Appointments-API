package com.api.v1.services.impl;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorAuditTrail;
import com.api.v1.domain.doctors.DoctorAuditTrailRepository;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.EmployedDoctorException;
import com.api.v1.services.doctors.DoctorRehiringService;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorRehiringServiceImpl implements DoctorRehiringService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;
    private final DoctorAuditTrailRepository doctorAuditTrailRepository;

    public DoctorRehiringServiceImpl(
            DoctorFinderUtil doctorFinderUtil,
            DoctorRepository doctorRepository,
            DoctorAuditTrailRepository doctorAuditTrailRepository
    ) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.doctorRepository = doctorRepository;
        this.doctorAuditTrailRepository = doctorAuditTrailRepository;
    }

    @Override
    public Mono<Void> rehire(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByActiveStatusAndLicenseNumber(medicalLicenseNumber)
                .flatMap(doctor -> {
                    if (doctor.getTerminatedAt() == null) {
                        return Mono.error(EmployedDoctorException::new);
                    }
                    DoctorAuditTrail auditTrail = DoctorAuditTrail.create(doctor);
                    return doctorAuditTrailRepository
                            .save(auditTrail)
                            .then(Mono.defer(() -> {
                                doctor.markAsRehired();
                                return doctorRepository.save(doctor);
                            }));
                })
                .then();
    }
}
