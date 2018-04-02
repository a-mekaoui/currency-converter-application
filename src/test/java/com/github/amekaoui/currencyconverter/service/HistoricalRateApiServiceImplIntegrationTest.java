package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoricalRateApiServiceImplIntegrationTest {

    @Autowired
    HistoricalRateApiService historicalRateApiService;

    @Test
    public void retrieveHistoricalRatesOf20180301Test() {
        try {
            Map<Currency, Map<Currency, BigDecimal>> rates = historicalRateApiService.retrieveHistoricalRates("01", "03", "2018", Currency.USD);
        } catch (Exception e) {
            Assert.fail("org.openexchangerates Unreachable or response JSON contract changed.");
        }
    }
}