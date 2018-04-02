package com.github.amekaoui.currencyconverter.domain;


import com.github.amekaoui.currencyconverter.domain.api.Currency;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "HISTORICAL_RATE")
@Access(AccessType.FIELD)
public class HistoricalRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "QUERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date queryDate;

    @Column(name = "QUERY_TYPE")
    @Enumerated(EnumType.STRING)
    private QueryType queryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_CREDENTIAL_EMAIL")
    private UserCredentialEntity userCredentialEntity;

    @Column(name = "SOURCE_AMOUNT")
    private BigDecimal sourceAmount;

    @Column(name = "TARGET_AMOUNT")
    private BigDecimal targetAmount;

    @Column(name = "SOURCE_CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency sourceCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "TARGET_CURRENCY")
    private Currency targetCurrency;

    @Column(name = "HISTORICAL_DATE")
    private String historicalDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public UserCredentialEntity getUserCredentialEntity() {
        return userCredentialEntity;
    }

    public void setUserCredentialEntity(UserCredentialEntity userCredentialEntity) {
        this.userCredentialEntity = userCredentialEntity;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setHistoricalDate(String historicalDate) {
        this.historicalDate = historicalDate;
    }

    public String getHistoricalDate() {
        return historicalDate;
    }
}
