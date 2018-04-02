package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.exception.ClientException;

import java.math.BigDecimal;
import java.util.Map;

public interface HistoricalRateApiService {

    Map<Currency, Map<Currency, BigDecimal>> retrieveHistoricalRates(String day, String mounth, String year, Currency baseCurrency) throws ClientException;
}
