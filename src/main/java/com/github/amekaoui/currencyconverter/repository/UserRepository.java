package com.github.amekaoui.currencyconverter.repository;

import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCredentialEntity, String> {
    UserCredentialEntity findByEmail(String email);
    UserCredentialEntity findUserCredentialByEmail(String email);
}