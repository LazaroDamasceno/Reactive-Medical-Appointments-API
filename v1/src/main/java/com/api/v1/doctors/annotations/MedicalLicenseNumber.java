package com.api.v1.doctors.annotations;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@NotNull
@Size(min = 10, max = 10)
@Pattern(regexp = "^\\d{8}[A-Z]{2}$")
public class MedicalLicenseNumber {
}
