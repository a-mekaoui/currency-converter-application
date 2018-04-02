package com.github.amekaoui.currencyconverter.controller;

import com.github.amekaoui.currencyconverter.form.UserCredentialCommand;
import com.github.amekaoui.currencyconverter.service.CountryService;
import com.github.amekaoui.currencyconverter.exception.EmailAddressAlreadyExistsException;
import com.github.amekaoui.currencyconverter.service.RegisterService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private Counter registrationSuccess = Metrics.counter("currency.converter.registration.success");
    private Counter registrationFailure = Metrics.counter("currency.converter.registration.failure");

    private RegisterService service;

    private CountryService countryService;

    public RegistrationController(@Lazy RegisterService registerService, CountryService countryService) {
        this.service = registerService;
        this.countryService = countryService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new UserCredentialCommand());
        return "signup";
    }

    @ModelAttribute("countries")
    protected List<String> getCountries() {
        return countryService.getCountriesNames();
    }

    @PostMapping("/signup")
    public String createUser(
            @Valid @ModelAttribute("customer") UserCredentialCommand userCredentialCommand,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            this.service.save(userCredentialCommand);
        } catch (EmailAddressAlreadyExistsException e) {
            bindingResult.rejectValue("email", "emailaddress.exists");
            LOGGER.info("Error while registering the user with the email address {}. Email already registered", userCredentialCommand.getEmail());
            registrationFailure.increment();
            return "signup";
        }

        registrationSuccess.increment();
        return "redirect:/";
    }
}
