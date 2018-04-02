package com.github.amekaoui.currencyconverter.validator;

import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;
import com.github.amekaoui.currencyconverter.form.AddressForm;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AgeValidatorUnitTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void anAgeOlderThan5OrGreaterThan100ShouldSpawnTheContraintTest() {
        UserCredentialCommand userCredentialCommand = new UserCredentialCommand();
        userCredentialCommand.setBirthDate(Date.from(LocalDate.of(2015, 3, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        userCredentialCommand.setEmail("test@test.com");
        userCredentialCommand.setPassword("12345678");
        userCredentialCommand.setConfirmPassword("12345678");
        AddressForm addressForm = new AddressForm();
        addressForm.setCity("test");
        addressForm.setCountry("test");
        addressForm.setStreet("test");
        addressForm.setZipCode("1234");
        userCredentialCommand.setAddressForm(addressForm);

        Set<ConstraintViolation<UserCredentialCommand>> constraintViolations =
                validator.validate(userCredentialCommand);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "age.error",
                constraintViolations.iterator().next().getMessage());

    }

}