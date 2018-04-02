package com.github.amekaoui.currencyconverter.form;

import com.github.amekaoui.currencyconverter.domain.QueryType;
import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.validator.DateFormatConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@DateFormatConstraint
public class ConversionForm {

    private String historyDate;

    private QueryType queryType;

    @NotNull(message = "{NotNull.sourceCurrency}")
    private Currency sourceCurrency;

    @NotNull(message = "{NotNull.targetCurrency}")
    private Currency targetCurrency;

    @NotNull(message = "{NotNull.sourceAmount}")
    private BigDecimal sourceAmount;

    private BigDecimal targetAmount;

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
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

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }
}
