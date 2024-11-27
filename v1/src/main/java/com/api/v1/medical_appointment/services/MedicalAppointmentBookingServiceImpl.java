package com.api.v1.medical_appointment.services;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.utils.CustomerFinderUtil;
import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.utils.DoctorFinderUtil;
import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final CustomerFinderUtil customerFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private MedicalAppointmentRepository medicalAppointmentRepository;

    @Override
    public Mono<MedicalAppointment> book(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Doctor doctor = tuple.getT2();
                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                            customer,
                            doctor,
                            bookingDto.bookingDate()
                    );
                    return medicalAppointmentRepository.save(medicalAppointment);
                });
    }

}
