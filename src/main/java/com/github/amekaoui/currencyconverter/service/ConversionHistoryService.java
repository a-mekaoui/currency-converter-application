package com.github.amekaoui.currencyconverter.service;

import com.github.amekaoui.currencyconverter.domain.HistoricalRateEntity;
import com.github.amekaoui.currencyconverter.domain.QueryType;
import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.form.ConversionForm;
import com.github.amekaoui.currencyconverter.form.HistoricalRateForm;
import com.github.amekaoui.currencyconverter.repository.CurrencyConversionSpecifications;
import com.github.amekaoui.currencyconverter.repository.HistoricalConversionRepository;
import com.github.amekaoui.currencyconverter.repository.UserRepository;
import com.github.amekaoui.currencyconverter.service.converter.HistoricalRateConverter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConversionHistoryService {

    private HistoricalConversionRepository historicalConversionRepository;

    private UserRepository userRepository;

    private HistoricalRateConverter historicalRateConverter;

    public ConversionHistoryService(HistoricalConversionRepository historicalConversionRepository, UserRepository userRepository, HistoricalRateConverter historicalRateConverter) {
        this.historicalConversionRepository = historicalConversionRepository;
        this.userRepository = userRepository;
        this.historicalRateConverter = historicalRateConverter;
    }

    public List<HistoricalRateForm> loadHistoricalConversions(String email) {
        UserCredentialEntity byEmail = this.userRepository.findByEmail(email);

        List<HistoricalRateEntity> conversionList = this.historicalConversionRepository
                .findAll(CurrencyConversionSpecifications.findHistoricalRateEntitiesByUserCredentialEmailSpecification(byEmail),
                        PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, "id")))
                .getContent();
        List<HistoricalRateForm> historicalRateForms = new ArrayList<>();

        conversionList.forEach(historicalRateEntity -> historicalRateForms.add(this.historicalRateConverter.convert(historicalRateEntity)));

        return historicalRateForms;
    }

    public void save(@Valid ConversionForm conversionForm, String email) {
        QueryType queryType = conversionForm.getHistoryDate() != null && conversionForm.getHistoryDate().trim() != "" ? QueryType.HISTORY : QueryType.LATEST;
        String historyDate = conversionForm.getHistoryDate();
        BigDecimal sourceAmount = conversionForm.getSourceAmount();
        BigDecimal targetAmount = conversionForm.getTargetAmount();
        Currency sourceCurrency = conversionForm.getSourceCurrency();
        Currency targetCurrency = conversionForm.getTargetCurrency();
        UserCredentialEntity userCredentialEntity = new UserCredentialEntity(email);
        HistoricalRateEntity historicalRateEntity = new HistoricalRateEntity();
        historicalRateEntity.setQueryType(queryType);
        historicalRateEntity.setQueryDate(new Date());
        historicalRateEntity.setUserCredentialEntity(userCredentialEntity);
        historicalRateEntity.setSourceAmount(sourceAmount);
        historicalRateEntity.setTargetAmount(targetAmount);
        historicalRateEntity.setSourceCurrency(sourceCurrency);
        historicalRateEntity.setTargetCurrency(targetCurrency);
        historicalRateEntity.setHistoricalDate(historyDate);

        this.historicalConversionRepository.save(historicalRateEntity);
    }
}
