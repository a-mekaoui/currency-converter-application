package com.github.amekaoui.currencyconverter.repository;

import com.github.amekaoui.currencyconverter.domain.HistoricalRateEntity;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRateRepository extends JpaRepository<HistoricalRateEntity, Long> {
    List<HistoricalRateEntity> findHistoricalRateEntitiesByUserCredentialEntity(String email);
}
