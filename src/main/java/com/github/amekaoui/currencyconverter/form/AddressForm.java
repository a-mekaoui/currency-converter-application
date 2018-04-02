package com.github.amekaoui.currencyconverter.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressForm {

    @NotNull
    @Size(min = 4, max = 100)
    private String street;
    @NotNull
    @Size(min = 3, max = 10)
    private String zipCode;
    @NotNull
    @Size(min = 3, max = 64)
    private String city;

    @NotBlank(message = "{NotNull.addressForm.country}")
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
