package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.latest.LatestRates;
import com.github.amekaoui.currencyconverter.exception.ClientException;
import com.github.amekaoui.currencyconverter.service.converter.LatestRateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "latestRateApiService")
public class LatestRateApiServiceImpl implements LatestRateApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LatestRateApiServiceImpl.class);
    @Value("${org.openexchangerates.latest.url}")
    private String latestRatesUrl;
    private RestTemplate restTemplate;

    public LatestRateApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable("latestRateApiService")
    public Map<Currency, Map<Currency, BigDecimal>> retrieveLatestRates(Currency baseCurrency) throws ClientException {
        String latestRatesQueryUrl = latestRatesUrl + "&base=" + baseCurrency.name();

        try {
            LatestRates latestRates = restTemplate.getForObject(latestRatesQueryUrl, LatestRates.class);
            return LatestRateConverter.convertLatestRates(latestRates);
        } catch (HttpClientErrorException | ResourceAccessException exception) {
            LOGGER.error("Error while contacting openexchangerates.org with the status code {}", exception.getMessage());
            throw new ClientException("Error while contacting openexchangerates.org", exception);
        }
    }
}