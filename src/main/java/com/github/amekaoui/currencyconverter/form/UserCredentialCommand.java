package com.github.amekaoui.currencyconverter.form;

import com.github.amekaoui.currencyconverter.validator.AgeConstraint;
import com.github.amekaoui.currencyconverter.validator.PasswordConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@PasswordConstraint
public class UserCredentialCommand {

    @NotBlank(message = "{NotBlank.userCredentialCommand.email}")
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Size(min = 8, max = 16)
    private String confirmPassword;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{NotNull.userCredentialCommand.birthDate}")
    @Past
    @AgeConstraint(min = 15, max = 90)
    private Date birthDate;

    @Valid
    private AddressForm addressForm;

    public AddressForm getAddressForm() {
        return addressForm;
    }

    public void setAddressForm(AddressForm addressForm) {
        this.addressForm = addressForm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}