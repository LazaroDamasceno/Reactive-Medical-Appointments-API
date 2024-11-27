package com.api.v1.medical_appointment.services;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.utils.CustomerFinderUtil;
import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.utils.DoctorFinderUtil;
import com.api.v1.medical_appointment.domain.MedicalAppointment;
import com.api.v1.medical_appointment.domain.MedicalAppointmentRepository;
import com.api.v1.medical_appointment.dtos.MedicalAppointmentBookingDto;
import com.api.v1.medical_appointment.exceptions.UnavailableBookingDateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final CustomerFinderUtil customerFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    @Override
    public Mono<MedicalAppointment> book(@Valid MedicalAppointmentBookingDto bookingDto) {
        Mono<Customer> customerMono = customerFinderUtil.find(bookingDto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(bookingDto.medicalLicenseNumber());
        return customerMono
                .zipWith(doctorMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Doctor doctor = tuple.getT2();
                    handleAlreadyFilledBookingDate(customer, doctor, bookingDto.bookingDate());
                    handleNotAllowedBookingDate(customer, doctor, bookingDto.bookingDate());
                    MedicalAppointment medicalAppointment = MedicalAppointment.create(
                            customer,
                            doctor,
                            bookingDto.bookingDate()
                    );
                    return medicalAppointmentRepository.save(medicalAppointment);
                });
    }

    private void handleAlreadyFilledBookingDate(Customer customer, Doctor doctor, LocalDateTime bookingDate) {
        String message = "The given booking date is already filled.";
        medicalAppointmentRepository
                .findAll()
                .filter(medicalAppointment -> medicalAppointment.getCustomer().equals(customer)
                        && medicalAppointment.getDoctor().equals(doctor)
                        && medicalAppointment.getBookedAt().equals(bookingDate)
                )
                .switchIfEmpty(Mono.error(new UnavailableBookingDateException(message)));
    }

    private void handleNotAllowedBookingDate(Customer customer, Doctor doctor, LocalDateTime bookingDate) {
        String message = "The given booking date is must be al least scheduled tomorrow.";
        medicalAppointmentRepository
                .findAll()
                .filter(medicalAppointment -> medicalAppointment.getCustomer().equals(customer)
                        && medicalAppointment.getDoctor().equals(doctor)
                        && medicalAppointment.getBookedAt().equals(bookingDate)
                        && medicalAppointment.getBookedAt().getYear() == LocalDateTime.now().getDayOfMonth()
                        && medicalAppointment.getBookedAt().getMonthValue() == LocalDateTime.now().getMonthValue()
                        && medicalAppointment.getBookedAt().getDayOfMonth() == LocalDateTime.now().getYear()
                )
                .switchIfEmpty(Mono.error(new UnavailableBookingDateException(message)));
    }

}
