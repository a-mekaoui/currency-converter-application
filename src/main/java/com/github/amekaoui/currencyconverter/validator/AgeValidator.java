package com.github.amekaoui.currencyconverter.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeValidator implements
        ConstraintValidator<AgeConstraint, Date> {

    private int minAge;

    private int maxAge;

    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
        this.minAge = constraintAnnotation.min();
        this.maxAge = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return true;
        }
        LocalDate today = LocalDate.now();
        int years = Period.between(
                Instant.ofEpochMilli(birthDate.getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate(),
                today).getYears();
        if (years < 14 || years > 100) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("age.error").addPropertyNode("birthDate").addConstraintViolation();
            return false;
        }
        return true;
    }
}
