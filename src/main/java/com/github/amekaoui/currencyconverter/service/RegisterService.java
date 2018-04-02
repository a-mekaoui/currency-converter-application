package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.exception.EmailAddressAlreadyExistsException;
import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;
import com.github.amekaoui.currencyconverter.repository.UserRepository;
import com.github.amekaoui.currencyconverter.service.converter.CustomerConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.text.MessageFormat;

@Service
public class RegisterService {

    private UserRepository repository;

    private CustomerConverter customerConverter;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterService(UserRepository repository, CustomerConverter customerConverter) { //}, SecurityService securityService) {
        this.repository = repository;
        this.customerConverter = customerConverter;
    }

    @Transactional
    public UserCredentialEntity save(@NotNull UserCredentialCommand userCredentialCommand) throws EmailAddressAlreadyExistsException {
        UserCredentialEntity userCredential = this.customerConverter.convert(userCredentialCommand);
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userCredential.setPassword(bCryptPasswordEncoder.encode(userCredential.getPassword()));
        userCredential.setAuthority("ROLE_USER");
        if (this.findByEmail(userCredential.getEmail()) != null) {
            throw new EmailAddressAlreadyExistsException(MessageFormat.format("This {0} email is already registered!", userCredential.getEmail()));
        }

        return this.repository.save(userCredential);
    }

    public UserCredentialEntity findByEmail(String email) {
        return this.repository.findByEmail(email);

    }
}