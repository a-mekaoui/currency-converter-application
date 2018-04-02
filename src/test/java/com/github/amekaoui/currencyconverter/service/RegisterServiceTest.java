package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.domain.UserCredentialTestUtils;
import com.github.amekaoui.currencyconverter.exception.EmailAddressAlreadyExistsException;
import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;
import com.github.amekaoui.currencyconverter.repository.UserRepository;
import com.github.amekaoui.currencyconverter.service.converter.CustomerConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class RegisterServiceTest {

    private RegisterService registerService;

    @Mock
    private UserRepository repository;

    @Mock
    private CustomerConverter customerConverter;

    @Before
    public void setUp() {
        // init mocks
        MockitoAnnotations.initMocks(this);

        registerService = new RegisterService(this.repository, this.customerConverter);
    }

    @Test
    public void save() throws EmailAddressAlreadyExistsException {
        UserCredentialCommand userCredentialCommand = UserCredentialTestUtils.initNewUserCredentialCommand();
        UserCredentialEntity userCredentialEntity = UserCredentialTestUtils.initNewUserCredentialEntity();
        UserCredentialEntity savedCredentialEntity = UserCredentialTestUtils.initNewUserCredentialEntity();
        savedCredentialEntity.setPassword("$2a$10$fx3DJpvpCo2QtrNMv5Rkp.H9FCToWzKDUgyCsy1Ugi9AmmM/U4doS");
        Mockito.when(this.customerConverter.convert(userCredentialCommand)).thenReturn(userCredentialEntity);
        Mockito.when(this.repository.save(any())).thenReturn(savedCredentialEntity);
        UserCredentialEntity savedUserCredentialEntity = this.registerService.save(userCredentialCommand);
        Assert.assertEquals("$2a$10$fx3DJpvpCo2QtrNMv5Rkp.H9FCToWzKDUgyCsy1Ugi9AmmM/U4doS", savedUserCredentialEntity.getPassword());
        Mockito.verify(this.repository, Mockito.times(1)).save(any());
        Mockito.verify(this.customerConverter, Mockito.times(1)).convert(any());
    }

    @Test
    public void findByEmail() {
        UserCredentialEntity userCredentialEntity = UserCredentialTestUtils.initNewUserCredentialEntity();
        Mockito.when(this.registerService.findByEmail("test@test.com")).thenReturn(userCredentialEntity);
        UserCredentialEntity foundCredential = this.registerService.findByEmail("test@test.com");
        Assert.assertEquals(userCredentialEntity, foundCredential);
        Mockito.verify(this.repository, Mockito.times(1)).findByEmail("test@test.com");
    }
}