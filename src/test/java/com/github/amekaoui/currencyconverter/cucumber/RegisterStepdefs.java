package com.github.amekaoui.currencyconverter.cucumber;

import com.github.amekaoui.currencyconverter.CurrencyConverterApplication;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CurrencyConverterApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@DirtiesContext
@Sql(scripts = "/test.sql")
@PropertySource("classpath:application.properties")

public class RegisterStepdefs {
    HtmlUnitDriver driver;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).build();
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }

    @Given("^I am on the register page$")
    public void i_am_on_the_register_page() {
        driver.get("http://localhost/signup");
    }

    @When("^I type the email address \"([^\"]*)\"$")
    public void i_type_the_email_address_test_at_test_dot_com(String email) {
        WebElement emailElement = driver.findElementById("email");
        emailElement.sendKeys(email);
    }

    @And("^I provide the birth date \"(.*?)\"$")
    public void i_provide_the_birth_date(String birthDate) {
        WebElement birthDateElement = driver.findElementById("birthdate");
        birthDateElement.sendKeys(birthDate);
    }


    @And("^I provide the street address \"(.*?)\"$")
    public void i_provide_the_street_address(String street) {
        WebElement streetAddress = driver.findElementById("street");
        streetAddress.sendKeys(street);
    }

    @And("^I provide the zip code \"(.*?)\"$")
    public void i_provide_the_zip_code(String zipCode) {
        WebElement zipCodeElement = driver.findElementById("zipCode");
        zipCodeElement.sendKeys(zipCode);
    }

    @And("^I provide the country \"(.*?)\"$")
    public void i_provide_the_country(String country) {
        WebElement countryElement = driver.findElementById("country");
        Select select = new Select(countryElement);
        select.selectByVisibleText(country);
    }

    @And("^I provide the city name \"(.*?)\"$")
    public void i_provide_the_city_name(String city) {
        WebElement emailElement = driver.findElementById("city");
        emailElement.sendKeys(city);
    }

    @And("^I provide the password  \"(.*?)\"$")
    public void i_provide_the_password(String password) {
        WebElement passwordElement = driver.findElementById("password");
        passwordElement.sendKeys(password);
    }

    @And("^I provide the password confirmation \"(.*?)\"$")
    public void i_provide_the_password_confirmation(String passwordConfirmation) {
        WebElement passwordConfirmationElement = driver.findElementById("confirmPassword");
        passwordConfirmationElement.sendKeys(passwordConfirmation);
    }

    @And("^I click \"(.*?)\"$")
    public void i_click(String elementName) {
        driver.findElementById(elementName).click();
    }

    @Then("^I should register as a user$")
    public void i_should_register_as_a_user() {
        Assert.assertEquals("http://localhost/login", driver.getCurrentUrl());
    }


    @When("^I follow \"(.*?)\"$")
    public void i_follow(String linkText) {
        driver.findElementByLinkText(linkText).click();
    }

    @Then("^I expect to see \"(.*?)\"$")
    public void i_expect_to_see(String text) {
        assertTrue(driver.findElementById("message").getText().equals(text));
    }
}