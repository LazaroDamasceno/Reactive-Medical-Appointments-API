package com.api.v1.controllers.medical_slots;

import com.api.v1.annotations.MongoDbId;
import com.api.v1.dtos.medical_slots.MedicalSlotRegistrationDto;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import com.api.v1.services.medical_slots.MedicalSlotCancellationService;
import com.api.v1.services.medical_slots.MedicalSlotRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-slots")
public class MedicalSlotController {

    @Autowired
    private MedicalSlotRegistrationService registrationService;

    @Autowired
    private MedicalSlotCancellationService cancellationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalSlotResponseDto> register(@Valid @RequestBody MedicalSlotRegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    @PatchMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Void> cancel(@MongoDbId @PathVariable String id) {
        return cancellationService.cancel(id);
    }

}
