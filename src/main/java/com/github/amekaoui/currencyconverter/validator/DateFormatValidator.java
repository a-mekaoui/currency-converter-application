package com.github.amekaoui.currencyconverter.validator;

import com.github.amekaoui.currencyconverter.domain.QueryType;
import com.github.amekaoui.currencyconverter.form.ConversionForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, ConversionForm> {
    private String format;
    private String message;
    private QueryType queryType;

    @Override
    public void initialize(DateFormatConstraint constraintAnnotation) {
        this.format = constraintAnnotation.format();
        this.message = constraintAnnotation.message();
        this.queryType = constraintAnnotation.queryType();
    }

    @Override
    public boolean isValid(ConversionForm conversionForm, ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.format);

        String historyDate = conversionForm.getHistoryDate();

        if (historyDate == null || historyDate.trim().equals("")) {
            return true;
        }

        try {
            LocalDate parsedLocalDate = LocalDate.parse(historyDate, formatter);
            // date in the future
            if (LocalDate.now().isBefore(parsedLocalDate)) {

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("date must be in past").addPropertyNode("historyDate").addConstraintViolation();
                this.message = "date must be in past";
                return false;
            }

        } catch (DateTimeParseException dateTimeParseException) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("invalid date").addPropertyNode("historyDate").addConstraintViolation();
            return false;
        }
        return true;
    }
}
