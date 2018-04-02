package com.github.amekaoui.currencyconverter.controller;

import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.exception.ClientException;
import com.github.amekaoui.currencyconverter.form.ConversionForm;
import com.github.amekaoui.currencyconverter.form.HistoricalRateForm;
import com.github.amekaoui.currencyconverter.service.ConversionHistoryService;
import com.github.amekaoui.currencyconverter.service.CurrencyConversionService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ConversionController {

    private final String CONVERSION_PAGE = "convert";
    private Counter conversionFailure = Metrics.counter("currency.converter.controller.conversion.failure");
    private Counter conversionSuccess = Metrics.counter("currency.converter.controller.conversion.success");
    private CurrencyConversionService conversionService;
    private ConversionHistoryService conversionHistoryService;

    public ConversionController(CurrencyConversionService conversionService, ConversionHistoryService conversionHistoryService) {
        this.conversionService = conversionService;
        this.conversionHistoryService = conversionHistoryService;
    }

    @ModelAttribute("basecurrencies")
    protected Currency[] getBaseCurrencies() {
        return Currency.values();
    }

    @ModelAttribute("targetcurrencies")
    protected Currency[] getTargetCurrecies() {
        return Currency.values();
    }

    @ModelAttribute("historicaldate")
    protected String getHistoricalDate() {
        String dateToParse = initHistoryCalDate();
        return dateToParse;
    }

    @ModelAttribute("sourceAmount")
    protected BigDecimal getSourceAmount() {
        return BigDecimal.ZERO;
    }

    @GetMapping("/conversion")
    public String initConversion(Model model, Principal loggedUser) {
        ConversionForm conversionForm = new ConversionForm();
        String parsedHistoryDate = "";
        conversionForm.setHistoryDate(parsedHistoryDate);
        model.addAttribute("conversionform", conversionForm);

        retrieveHistoricalConversions(model, loggedUser);
        model.addAttribute(("conversionform"), conversionForm);
        return CONVERSION_PAGE;
    }

    @PostMapping("/conversion")
    public String doConversion(@Valid @ModelAttribute("conversionform") ConversionForm conversionForm,
                               BindingResult bindingResult,
                               Model model, Principal loggedUser) {
        if (bindingResult.hasErrors()) {
            retrieveHistoricalConversions(model, loggedUser);
            model.addAttribute(("conversionform"), conversionForm);
            conversionFailure.increment();
            return CONVERSION_PAGE;
        }
        try {
            final BigDecimal convertedAmount = this.conversionService.convert(
                    conversionForm.getHistoryDate(),
                    conversionForm.getSourceCurrency(),
                    conversionForm.getSourceAmount(),
                    conversionForm.getTargetCurrency());
            conversionForm.setTargetAmount(convertedAmount);
            this.conversionHistoryService.save(conversionForm,
                    loggedUser.getName());
            retrieveHistoricalConversions(model, loggedUser);
            model.addAttribute(("conversionform"), conversionForm);

            conversionSuccess.increment();

        } catch (ClientException e) {
            retrieveHistoricalConversions(model, loggedUser);
            bindingResult.reject("openexchange.problem");
            model.addAttribute(("conversionform"), conversionForm);
        }
        return CONVERSION_PAGE;
    }

    private void retrieveHistoricalConversions(Model model, Principal loggedUser) {
        List<HistoricalRateForm> historicalRateForms = this.conversionHistoryService.loadHistoricalConversions(loggedUser.getName());
        model.addAttribute("historicalRateForms", historicalRateForms);
    }

    private String initHistoryCalDate() {
        LocalDate historyDate = LocalDate.now();
        return historyDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
