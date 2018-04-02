package com.github.amekaoui.currencyconverter.service.converter;

import com.github.amekaoui.currencyconverter.domain.HistoricalRateEntity;
import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.historical.HistoricalRates;
import com.github.amekaoui.currencyconverter.form.HistoricalRateForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class HistoricalRateConverter implements Converter<HistoricalRateEntity, HistoricalRateForm> {
    public static Map<Currency, Map<Currency, BigDecimal>> convertHistoricalRates(HistoricalRates historicalRates) {
        Map<Currency, Map<Currency, BigDecimal>> container = new HashMap<>();

        Map<Currency, BigDecimal> rates = new HashMap<>();
        rates.put(Currency.AED, historicalRates.getRates().getAED());
        rates.put(Currency.AUD, historicalRates.getRates().getAUD());
        rates.put(Currency.EUR, historicalRates.getRates().getEUR());
        rates.put(Currency.GBP, historicalRates.getRates().getGBP());
        rates.put(Currency.NZD, historicalRates.getRates().getNZD());
        rates.put(Currency.USD, historicalRates.getRates().getUSD());
        rates.put(Currency.JPY, historicalRates.getRates().getJPY());

        container.put(Currency.valueOf(historicalRates.getBase()), rates);
        return container;
    }
    @Override
    public HistoricalRateForm convert(HistoricalRateEntity historicalRateEntity) {
        HistoricalRateForm historicalRateForm = new HistoricalRateForm();
        historicalRateForm.setHistoricalDate(historicalRateEntity.getHistoricalDate());
        historicalRateForm.setQueryDate(historicalRateEntity.getQueryDate());
        historicalRateForm.setQueryType(historicalRateEntity.getQueryType());
        historicalRateForm.setSourceAmount(historicalRateEntity.getSourceAmount());
        historicalRateForm.setTargetAmount(historicalRateEntity.getTargetAmount());
        historicalRateForm.setSourceCurrency(historicalRateEntity.getSourceCurrency());
        historicalRateForm.setTargetCurrency(historicalRateEntity.getTargetCurrency());
        historicalRateForm.setQueryType(historicalRateEntity.getQueryType());
        return historicalRateForm;
    }
}
