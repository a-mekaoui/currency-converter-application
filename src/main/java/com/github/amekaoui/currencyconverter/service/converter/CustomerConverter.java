package com.github.amekaoui.currencyconverter.service.converter;


import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<UserCredentialCommand, UserCredentialEntity> {

    @Autowired
    private AddressConverter addressConverter;

    @Override
    public UserCredentialEntity convert(UserCredentialCommand userCredentialCommand) {

        UserCredentialEntity userCredential;
        userCredential = new UserCredentialEntity(userCredentialCommand.getEmail(),
                userCredentialCommand.getPassword(),
                userCredentialCommand.getBirthDate(),
                addressConverter.convert(userCredentialCommand.getAddressForm()));
        return userCredential;
    }
}
