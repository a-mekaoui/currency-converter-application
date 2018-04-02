package com.github.amekaoui.currencyconverter.repository;

import com.github.amekaoui.currencyconverter.domain.HistoricalRateEntity;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalConversionRepository extends CrudRepository<HistoricalRateEntity, Long>, JpaSpecificationExecutor<HistoricalRateEntity> {
}
