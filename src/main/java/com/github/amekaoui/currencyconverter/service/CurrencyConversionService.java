package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.exception.ClientException;

import java.math.BigDecimal;

public interface CurrencyConversionService {
    BigDecimal convert(String historicalDate, Currency sourceCurrency, BigDecimal sourceAmount, Currency targetCurrency) throws ClientException;
}
