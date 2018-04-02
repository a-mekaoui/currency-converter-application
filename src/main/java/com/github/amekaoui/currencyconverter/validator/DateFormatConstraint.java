package com.github.amekaoui.currencyconverter.validator;

import com.github.amekaoui.currencyconverter.domain.QueryType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatConstraint {
    String message() default "Invalid date";

    QueryType queryType() default QueryType.HISTORY;

    String format() default "dd/MM/yyyy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
