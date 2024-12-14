package com.api.v1.services.impl;

import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.exceptions.medical_slots.UnavailableMedicalSlotException;
import com.api.v1.exceptions.medical_appointments.MedicalAppointmentInvalidBookingDateTimeException;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.services.medical_appointments.MedicalAppointmentBookingService;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
                   Customer customer = tuple.getT1();
                   Doctor doctor = tuple.getT2();
                   return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                           .then(onInadequateBookingDateTime(bookingDto.bookingDate()))
                           .then(Mono.defer(() -> {
                               MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                       customer,
                                       doctor,
                                       bookingDto.bookingDate(),
                                       "Medical appointment covered by the customer."
                               );
                              return medicalAppointmentRepository.save(medicalAppointment);
                           }));
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
                    Customer customer = tuple.getT1();
                    Doctor doctor = tuple.getT2();
                    return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                            .then(onInadequateBookingDateTime(bookingDto.bookingDate()))
                            .then(Mono.defer(() -> {
                                MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                        customer,
                                        doctor,
                                        bookingDto.bookingDate(),
                                        "Medical appointment covered by the Affordable Care Act (ACA)."
                                );
                                return medicalAppointmentRepository.save(medicalAppointment);
                            }));
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
                    Customer customer = tuple.getT1();
                    Doctor doctor = tuple.getT2();
                    return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                            .then(onInadequateBookingDateTime(bookingDto.bookingDate()))
                            .then(Mono.defer(() -> {
                                MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                        customer,
                                        doctor,
                                        bookingDto.bookingDate(),
                                        "Medical appointment covered by the private health care."
                                );
                                return medicalAppointmentRepository.save(medicalAppointment);
                            }));
                })
                .flatMap(MedicalAppointmentResponseMapper::mapToMono);
    }

    private Mono<Object> onDuplicatedBookingDate(Customer customer, Doctor doctor, String bookedAt) {
        return medicalAppointmentRepository
                .findAll()
                .filter(ma -> ma.getDoctor().getId().equals(doctor.getId())
                        && ma.getCustomer().getId().equals(customer.getId())
                        && ma.getBookedAt().equals(bookedAt)
                        && ma.getCanceledAt() == null
                )
                .hasElements()
                .flatMap(exists -> {
                    String message = """
                        Medical slot whose doctor's license number is %s and booked datetime is %s is already occupied.
                    """.formatted(doctor.getLicenseNumber(), bookedAt);
                    if (exists) return Mono.error(new UnavailableMedicalSlotException(message));
                    return Mono.empty();
                });
    }

    private Mono<Object> onInadequateBookingDateTime(LocalDateTime bookedAt) {
        var day = LocalDateTime.now().getDayOfMonth();
        var month = LocalDateTime.now().getMonthValue();
        var year = LocalDateTime.now().getYear();
        var isGivenEqualsOrLessThanToday = bookedAt.getYear() == year
                && bookedAt.getMonthValue() == month
                && bookedAt.getDayOfMonth() == day;
        if (!isGivenEqualsOrLessThanToday) return Mono.empty();
        return Mono.error(MedicalAppointmentInvalidBookingDateTimeException::new);
    }
}
