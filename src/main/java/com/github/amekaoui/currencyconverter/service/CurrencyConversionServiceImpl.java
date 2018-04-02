package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.exception.ClientException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    private LatestRateApiService latestRateApiService;

    private HistoricalRateApiServiceImpl historicalRateApiService;

    public CurrencyConversionServiceImpl(LatestRateApiService latestRateApiService, HistoricalRateApiServiceImpl historicalRateApiService) {
        this.latestRateApiService = latestRateApiService;
        this.historicalRateApiService = historicalRateApiService;
    }

    @Override
    public BigDecimal convert(String historicalDate, Currency sourceCurrency, BigDecimal sourceAmount, Currency targetCurrency) throws ClientException {
        BigDecimal result;

        if (historicalDate != null && !historicalDate.trim().equals("")) {
            result = this.handleHistoricalConversion(historicalDate, sourceCurrency, sourceAmount, targetCurrency);
        } else {
            result = this.handleLatestConversion(sourceCurrency, sourceAmount, targetCurrency);
        }
        return result;
    }

    private BigDecimal handleHistoricalConversion(@NotEmpty String historicalDate, Currency sourceCurrency, BigDecimal sourceAmount, Currency targetCurrency) throws ClientException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(historicalDate, formatter);
        String dateToParse = parsedDate.format(formatter);
        String day = dateToParse.substring(0, dateToParse.indexOf('/'));
        String month = dateToParse.substring(dateToParse.indexOf('/') + 1, dateToParse.lastIndexOf("/"));
        String year = dateToParse.substring(dateToParse.lastIndexOf('/') + 1);
        Map<Currency, Map<Currency, BigDecimal>> latestCurrencyRatesMap = this.historicalRateApiService.retrieveHistoricalRates(day, month, year, sourceCurrency);
        BigDecimal targetCurrencyValue = latestCurrencyRatesMap.get(sourceCurrency).get(targetCurrency);

        return sourceAmount.multiply(targetCurrencyValue, new MathContext(6,
                RoundingMode.HALF_EVEN));
    }

    private BigDecimal handleLatestConversion(Currency sourceCurrency, BigDecimal sourceAmount, Currency targetCurrency) throws ClientException {
        Map<Currency, Map<Currency, BigDecimal>> currencyMapMap = this.latestRateApiService.retrieveLatestRates(sourceCurrency);
        BigDecimal targetCurrencyValue = currencyMapMap.get(sourceCurrency).get(targetCurrency);

        return sourceAmount.multiply(targetCurrencyValue, new MathContext(6,
                RoundingMode.HALF_EVEN));
    }
}
