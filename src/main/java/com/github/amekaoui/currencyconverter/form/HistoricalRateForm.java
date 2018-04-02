package com.github.amekaoui.currencyconverter.form;


import com.github.amekaoui.currencyconverter.domain.QueryType;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.domain.api.Currency;

import java.math.BigDecimal;
import java.util.Date;

public class HistoricalRateForm {

    private Date queryDate;

    private QueryType queryType;

    private UserCredentialEntity userCredentialEntity;

    private BigDecimal sourceAmount;

    private BigDecimal targetAmount;

    private Currency sourceCurrency;

    private Currency targetCurrency;

    private String historicalDate;

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

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setHistoricalDate(String historicalDate) {
        this.historicalDate = historicalDate;
    }

    public String getHistoricalDate() {
        return historicalDate;
    }
}
