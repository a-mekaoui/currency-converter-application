package com.github.amekaoui.currencyconverter.validator;

import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserCredentialCommand> {

    @Override
    public boolean isValid(UserCredentialCommand user, ConstraintValidatorContext context) {
        boolean result = true;
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()
                && !user.getPassword().equals(user.getConfirmPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("password.mismatch").addPropertyNode("confirmPassword").addConstraintViolation();
            result = false;
        }
        return result;
    }
}
