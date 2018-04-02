package com.github.amekaoui.currencyconverter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserCredentialEntityTest {

    private UserCredentialEntity userCredentialEntity;

    @Before
    public void setUp() {
        userCredentialEntity = new UserCredentialEntity();
    }

    @Test
    public void getEmail() {
        String actual = "test@test.com";
        this.userCredentialEntity.setEmail(actual);
        assertEquals("test@test.com", this.userCredentialEntity.getEmail());
    }

    @Test
    public void getAuthority() {
        String actual = "USER";
        this.userCredentialEntity.setAuthority(actual);
        assertEquals("USER", this.userCredentialEntity.getAuthority());
    }

    @Test
    public void getPassword() {
        String actual = "testtest";
        this.userCredentialEntity.setPassword(actual);
        assertEquals("testtest", this.userCredentialEntity.getPassword());
    }

    @Test
    public void getBirthDate() {
        Date date = new Date();
        this.userCredentialEntity.setBirthDate(date);
        assertEquals(date, this.userCredentialEntity.getBirthDate());
    }

    @Test
    public void getAddress() {
        Address address = initNewAddress();
        this.userCredentialEntity.setAddress(address);
        assertEquals(address, this.userCredentialEntity.getAddress());
    }


    @Test
    public void constructorWithEmailParam() {
        this.userCredentialEntity = new UserCredentialEntity("test@test.com");
        assertEquals("test@test.com", this.userCredentialEntity.getEmail());
    }

    @Test
    public void constructorWithParam() {
        Address address = this.initNewAddress();
        this.userCredentialEntity = new UserCredentialEntity("test@test.com", "testtest", new Date(), address);
        assertEquals("test@test.com", this.userCredentialEntity.getEmail());
        assertEquals(address, this.userCredentialEntity.getAddress());
    }

    private Address initNewAddress() {
        Address address = new Address();
        address.setCity("Uccle");
        address.setStreet("some street");
        address.setZipCode("1180");
        address.setCountry("Belgium");
        return address;
    }
}