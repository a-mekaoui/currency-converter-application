package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.exception.ClientException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

public interface LatestRateApiService {

    Map<Currency, Map<Currency, BigDecimal>> retrieveLatestRates(Currency baseCurrency) throws ClientException;
}
