package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.historical.HistoricalRates;
import com.github.amekaoui.currencyconverter.exception.ClientException;
import com.github.amekaoui.currencyconverter.service.converter.HistoricalRateConverter;
import com.github.amekaoui.currencyconverter.service.utils.PropertyFileReplacementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "historicalRateApi")
public class HistoricalRateApiServiceImpl implements HistoricalRateApiService {

    @Value("${org.openexchangerates.historical.url}")
    String historicalRatesUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoricalRateApiServiceImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cacheManager;

    public HistoricalRateApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("historicalRateApi")
    @Override
    public Map<Currency, Map<Currency, BigDecimal>> retrieveHistoricalRates(String day, String month, String year, Currency baseCurrency) throws ClientException {
        String historicalRatesQueryUrl = PropertyFileReplacementUtils.replaceHistoricalUrlProperty(historicalRatesUrl, day, month, year);
        historicalRatesQueryUrl = historicalRatesQueryUrl + "&base=" + baseCurrency.name();
        LOGGER.info("using cache manager: {} ", cacheManager.getClass().getName() );

        try {
            HistoricalRates historicalRates = restTemplate.getForObject(historicalRatesQueryUrl, HistoricalRates.class);
            return HistoricalRateConverter.convertHistoricalRates(historicalRates);
        } catch (HttpClientErrorException|ResourceAccessException exception) {
            LOGGER.error("Error while contacting openexchangerates.org with the status code {}", exception.getMessage());
            throw new ClientException("Error while contacting openexchangerates.org", exception);
        }
    }


}
