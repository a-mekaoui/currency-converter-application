package com.github.amekaoui.currencyconverter.domain;

import com.github.amekaoui.currencyconverter.form.AddressForm;
import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;

import java.util.Date;

public class UserCredentialTestUtils {

    public static UserCredentialEntity initNewUserCredentialEntity() {
        Address address = initNewAddress();
        UserCredentialEntity userCredentialEntity = new UserCredentialEntity("test@test.com", "testtest", new Date(), address);
        return userCredentialEntity;
    }
    public static UserCredentialEntity initNewUserCredentialEntityWithTest2AtTestDotComEmail() {
        Address address = initNewAddress();
        UserCredentialEntity userCredentialEntity = new UserCredentialEntity("test2@test.com", "$2a$10$fx3DJpvpCo2QtrNMv5Rkp.H9FCToWzKDUgyCsy1Ugi9AmmM/U4doS", new Date(), address);
        userCredentialEntity.setAuthority("ROLE_USER");
        return userCredentialEntity;
    }

    public static UserCredentialCommand initNewUserCredentialCommand() {
        AddressForm address = initNewAddressForm();
        UserCredentialCommand userCredentialCommand = new UserCredentialCommand();
        userCredentialCommand.setEmail("test@test.com");
        userCredentialCommand.setAddressForm(address);
        userCredentialCommand.setPassword("testtest");
        userCredentialCommand.setConfirmPassword("testtest");
        userCredentialCommand.setBirthDate(new Date());
        return userCredentialCommand;
    }

    private static AddressForm initNewAddressForm() {
        AddressForm address = new AddressForm();
        address.setCity("Uccle");
        address.setStreet("some street");
        address.setZipCode("1180");
        address.setCountry("Belgium");
        return address;
    }


    private static Address initNewAddress() {
        Address address = new Address();
        address.setCity("Uccle");
        address.setStreet("some street");
        address.setZipCode("1180");
        address.setCountry("Belgium");
        return address;
    }
}
