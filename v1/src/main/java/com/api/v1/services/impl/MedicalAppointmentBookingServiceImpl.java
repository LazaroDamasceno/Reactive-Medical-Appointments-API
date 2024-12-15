package com.api.v1.services.impl;

import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.exceptions.medical_slots.UnavailableMedicalSlotException;
import com.api.v1.exceptions.medical_appointments.InvalidMedicalAppointmentBookingDateTimeException;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.services.medical_appointments.MedicalAppointmentBookingService;
import com.api.v1.domain.medical_appointments.MedicalAppointmentRepository;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final CustomerFinderUtil customerFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MedicalSlotFinderUtil medicalSlotFinderUtil;
    private final MedicalSlotRepository medicalSlotRepository;

    public MedicalAppointmentBookingServiceImpl(
            CustomerFinderUtil customerFinderUtil,
            DoctorFinderUtil doctorFinderUtil,
            MedicalAppointmentRepository medicalAppointmentRepository,
            MedicalSlotFinderUtil medicalSlotFinderUtil,
            MedicalSlotRepository medicalSlotRepository
    ) {
        this.customerFinderUtil = customerFinderUtil;
        this.doctorFinderUtil = doctorFinderUtil;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.medicalSlotFinderUtil = medicalSlotFinderUtil;
        this.medicalSlotRepository = medicalSlotRepository;
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
                   return medicalSlotFinderUtil
                           .find(doctor.getId(), bookingDto.bookingDate().toString())
                           .flatMap(medicalSlot -> {
                               return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                                       .then()
                                       .then(onInvalidBookingDateTime(bookingDto.bookingDate()))
                                       .then(Mono.defer(() -> {
                                            MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                                    customer,
                                                    doctor,
                                                    bookingDto.bookingDate(),
                                                    "Medical appointment covered by the customer."
                                            );
                                            medicalSlot.setMedicalAppointment(medicalAppointment);
                                            return medicalSlotRepository
                                                    .save(medicalSlot)
                                                    .then(Mono.defer(() -> {
                                                        return medicalAppointmentRepository.save(medicalAppointment);
                                                    }));
                                       }));
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
           Customer customer = tuple.getT1();
           Doctor doctor = tuple.getT2();
           return medicalSlotFinderUtil
                   .find(doctor.getId(), bookingDto.bookingDate().toString())
                   .flatMap(medicalSlot -> {
                       return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                               .then()
                               .then(onInvalidBookingDateTime(bookingDto.bookingDate()))
                               .then(Mono.defer(() -> {
                                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                            customer,
                                            doctor,
                                            bookingDto.bookingDate(),
                                            "Medical appointment covered by the Affordable Care Act (ACA)."
                                    );
                                    medicalSlot.setMedicalAppointment(medicalAppointment);
                                    return medicalSlotRepository
                                            .save(medicalSlot)
                                            .then(Mono.defer(() -> {
                                                return medicalAppointmentRepository.save(medicalAppointment);
                                            }));
                               }));
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
           Customer customer = tuple.getT1();
           Doctor doctor = tuple.getT2();
           return medicalSlotFinderUtil
                   .find(doctor.getId(), bookingDto.bookingDate().toString())
                   .flatMap(medicalSlot -> {
                       return onDuplicatedBookingDate(customer, doctor, bookingDto.bookingDate().toString())
                               .then()
                               .then(onInvalidBookingDateTime(bookingDto.bookingDate()))
                               .then(Mono.defer(() -> {
                                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                                            customer,
                                            doctor,
                                            bookingDto.bookingDate(),
                                            "Medical appointment covered by the private healthcare."
                                    );
                                    medicalSlot.setMedicalAppointment(medicalAppointment);
                                    return medicalSlotRepository
                                            .save(medicalSlot)
                                            .then(Mono.defer(() -> {
                                                return medicalAppointmentRepository.save(medicalAppointment);
                                            }));
                               }));
                   });
        })
        .flatMap(MedicalAppointmentResponseMapper::mapToMono);    }

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

    private Mono<Object> onInvalidBookingDateTime(LocalDateTime bookedAt) {
        var day = LocalDateTime.now().getDayOfMonth();
        var month = LocalDateTime.now().getMonthValue();
        var year = LocalDateTime.now().getYear();
        var isGivenEqualsOrLessThanToday = bookedAt.getYear() == year
                && bookedAt.getMonthValue() == month
                && bookedAt.getDayOfMonth() == day;
        if (!isGivenEqualsOrLessThanToday) return Mono.empty();
        return Mono.error(InvalidMedicalAppointmentBookingDateTimeException::new);
    }
}
