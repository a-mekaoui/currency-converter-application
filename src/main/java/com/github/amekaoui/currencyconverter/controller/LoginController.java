package com.github.amekaoui.currencyconverter.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Principal;

@Controller
public class LoginController implements WebMvcConfigurer {

    private Counter loginSuccess = Metrics.counter("currency.controller.converter.login.success");
    private Counter loginFailure = Metrics.counter("currency.controller.converter.login.failure");

    @RequestMapping("/")
    public String home(Principal principal) {

        if (principal == null) {
            loginFailure.increment();
            return "redirect:login";
        }
        loginSuccess.increment();
        return "redirect:/conversion";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
