package com.api.v1.services.impl;

import com.api.v1.domain.customers.Customer;
import com.api.v1.exceptions.medical_appointments.UnavailableSlotException;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.services.medical_appointments.MedicalAppointmentBookingService;
import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final CustomerFinderUtil customerFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentBookingServiceImpl(
            CustomerFinderUtil customerFinderUtil,
            DoctorFinderUtil doctorFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository
    ) {
        this.customerFinderUtil = customerFinderUtil;
        this.doctorFinderUtil = doctorFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Mono<MedicalAppointmentResponseDto> bookPaidMedicalAppointment(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> {
                    return medicalAppointmentRepository
                            .findAll()
                            .filter(ma ->
                                    ma.getDoctor().getId().equals(tuple.getT2().getId())
                                    && ma.getCustomer().getId().equals(tuple.getT1().getId())
                                    && ma.getBookedAt().equals(bookingDto.bookingDate().toString())
                                    && ma.getCanceledAt() == null
                            )
                            .hasElements()
                            .flatMap(exists -> {
                               if (exists) return Mono.error(new UnavailableSlotException(
                                       bookingDto.bookingDate(),
                                       bookingDto.medicalLicenseNumber())
                               );
                               return Mono.defer(() -> {
                                   Customer customer = tuple.getT1();
                                   Doctor doctor = tuple.getT2();
                                   MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                           customer,
                                           doctor,
                                           bookingDto.bookingDate(),
                                           "Medical appointment covered by the customer."
                                   );
                                   return medicalAppointmentRepository.save(medicalAppointment);
                            });
                    });
                })
                .flatMap(MedicalAppointmentResponseMapper::mapToMono);
    }

    @Override
    public Mono<MedicalAppointmentResponseDto> bookAffordableMedicalAppointment(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> {
                    return medicalAppointmentRepository
                            .findAll()
                            .filter(ma ->
                                    ma.getDoctor().getId().equals(tuple.getT2().getId())
                                            && ma.getCustomer().getId().equals(tuple.getT1().getId())
                                            && ma.getBookedAt().equals(bookingDto.bookingDate().toString())
                                            && ma.getCanceledAt() == null
                            )
                            .hasElements()
                            .flatMap(exists -> {
                                if (exists) return Mono.error(new UnavailableSlotException(
                                        bookingDto.bookingDate(),
                                        bookingDto.medicalLicenseNumber())
                                );
                                return Mono.defer(() -> {
                                    Customer customer = tuple.getT1();
                                    Doctor doctor = tuple.getT2();
                                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                            customer,
                                            doctor,
                                            bookingDto.bookingDate(),
                                            "Medical appointment covered by the Affordable Care Act (ACA)."
                                    );
                                    return medicalAppointmentRepository.save(medicalAppointment);
                                });
                            });
                })
                .flatMap(MedicalAppointmentResponseMapper::mapToMono);
    }

    @Override
    public Mono<MedicalAppointmentResponseDto> bookPrivateHeathCareMedicalAppointment(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> {
                    return medicalAppointmentRepository
                            .findAll()
                            .filter(ma ->
                                    ma.getDoctor().getId().equals(tuple.getT2().getId())
                                            && ma.getCustomer().getId().equals(tuple.getT1().getId())
                                            && ma.getBookedAt().equals(bookingDto.bookingDate().toString())
                                            && ma.getCanceledAt() == null
                            )
                            .hasElements()
                            .flatMap(exists -> {
                                if (exists) return Mono.error(new UnavailableSlotException(
                                        bookingDto.bookingDate(),
                                        bookingDto.medicalLicenseNumber())
                                );
                                return Mono.defer(() -> {
                                    Customer customer = tuple.getT1();
                                    Doctor doctor = tuple.getT2();
                                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                            customer,
                                            doctor,
                                            bookingDto.bookingDate(),
                                            "Medical appointment covered by the private healthcare."
                                    );
                                    return medicalAppointmentRepository.save(medicalAppointment);
                                });
                            });
                })
                .flatMap(MedicalAppointmentResponseMapper::mapToMono);
    }
}
