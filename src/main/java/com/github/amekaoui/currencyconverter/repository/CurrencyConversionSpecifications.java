package com.github.amekaoui.currencyconverter.repository;

import com.github.amekaoui.currencyconverter.domain.HistoricalRateEntity;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CurrencyConversionSpecifications {

    public static Specification<HistoricalRateEntity> findHistoricalRateEntitiesByUserCredentialEmailSpecification(
            final UserCredentialEntity email) {
        return (Root<HistoricalRateEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> cb.equal(root.get("userCredentialEntity"), email);
    }
}