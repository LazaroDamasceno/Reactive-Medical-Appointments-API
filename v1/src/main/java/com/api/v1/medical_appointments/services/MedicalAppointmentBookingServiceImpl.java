package com.api.v1.medical_appointments.services;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.utils.CustomerFinderUtil;
import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.utils.DoctorFinderUtil;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentBookingDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final CustomerFinderUtil customerFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    @Override
    public Mono<MedicalAppointment> bookPaidMedicalAppointment(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> Mono.defer(() -> {
                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                            tuple.getT1(),
                            tuple.getT2(),
                            bookingDto.bookingDate(),
                            "Medical appointment covered by the customer"
                    );
                    return medicalAppointmentRepository.save(medicalAppointment);
                }));
    }

    @Override
    public Mono<MedicalAppointment> bookAffordableMedicalAppointment(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> Mono.defer(() -> {
                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                            tuple.getT1(),
                            tuple.getT2(),
                            bookingDto.bookingDate(),
                            "Medical appointment covered by the Affordable Care Act"
                    );
                    return medicalAppointmentRepository.save(medicalAppointment);
                }));
    }

    @Override
    public Mono<MedicalAppointment> bookPrivateHeathCareMedicalAppointment(MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> Mono.defer(() -> {
                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                            tuple.getT1(),
                            tuple.getT2(),
                            bookingDto.bookingDate(),
                            "Medical appointment covered by private health care"
                    );
                    return medicalAppointmentRepository.save(medicalAppointment);
                }));
    }
}