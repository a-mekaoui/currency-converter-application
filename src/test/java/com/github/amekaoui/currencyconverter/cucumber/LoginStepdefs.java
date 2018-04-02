package com.github.amekaoui.currencyconverter.cucumber;

import com.github.amekaoui.currencyconverter.CurrencyConverterApplication;
import com.github.amekaoui.currencyconverter.domain.UserCredentialTestUtils;
import com.github.amekaoui.currencyconverter.repository.UserRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyConverterApplication.class}, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@DirtiesContext
@PropertySource("classpath:application.properties")

public class LoginStepdefs {
    HtmlUnitDriver driver;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws IOException {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).javascriptEnabled(true).build();
        userRepository.save(UserCredentialTestUtils.initNewUserCredentialEntityWithTest2AtTestDotComEmail());
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }

    @Given("^I am on the login page$")
    public void i_am_on_the_login_page() throws Throwable {
        driver.get("http://localhost/login");
    }

    @When("^I enter the email address \"([^\"]*)\"$")
    public void i_enter_the_email_address_test_at_test_dot_com(String email) throws Throwable {
        //driver.get("http://localhost/signup");
        WebElement emailElement = driver.findElementById("email");
        emailElement.sendKeys(email);
    }

    @And("^I provide the password \"(.*?)\"$")
    public void i_provide_the_password(String password) throws Throwable {
        WebElement passwordElement = driver.findElementById("password");
        passwordElement.sendKeys(password);
    }

    @And("^I click at \"(.*?)\"$")
    public void i_click_at(String elementName) throws Throwable {
        driver.findElementById(elementName).sendKeys(Keys.RETURN);//.click();
    }

    @Then("^I should be logged in and the current page is conversion$")
    public void i_should_be_logged_in_and_the_current_page_is_conversion() throws Throwable {
        Assert.assertEquals("http://localhost/conversion", driver.getCurrentUrl());
    }
}
