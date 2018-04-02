package com.github.amekaoui.currencyconverter.service.converter;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.latest.LatestRates;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LatestRateConverter {
    public static Map<Currency, Map<Currency, BigDecimal>> convertLatestRates(LatestRates latestRates) {
        Map<Currency, Map<Currency, BigDecimal>> container = new HashMap<>();

        Map<Currency, BigDecimal> rates = new HashMap<>();
        rates.put(Currency.AED, latestRates.getRates().getAED());
        rates.put(Currency.AUD, latestRates.getRates().getAUD());
        rates.put(Currency.EUR, latestRates.getRates().getEUR());
        rates.put(Currency.GBP, latestRates.getRates().getGBP());
        rates.put(Currency.NZD, latestRates.getRates().getNZD());
        rates.put(Currency.USD, latestRates.getRates().getUSD());
        rates.put(Currency.JPY, latestRates.getRates().getJPY());

        container.put(Currency.valueOf(latestRates.getBase()), rates);
        return container;
    }
}
