package com.api.v1.annotations;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@NotNull
@Size(min = 10, max = 10)
@Pattern(regexp = "^\\d{8}[A-Z]{2}$")
public @interface MedicalLicenseNumber {
}
