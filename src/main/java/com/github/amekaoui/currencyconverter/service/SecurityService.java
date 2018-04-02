package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class SecurityService implements UserDetailsService {

    protected UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull String email)
            throws UsernameNotFoundException {
        UserCredentialEntity foundUserCredential = this.userRepository.findUserCredentialByEmail(email);

        if (foundUserCredential == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(foundUserCredential.getEmail(), foundUserCredential.getPassword(),
                AuthorityUtils.createAuthorityList(foundUserCredential.getAuthority()));
    }
}