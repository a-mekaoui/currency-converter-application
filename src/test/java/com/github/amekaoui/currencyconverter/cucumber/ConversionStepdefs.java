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
import org.openqa.selenium.support.ui.Select;
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

public class ConversionStepdefs {
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

    @Given("^I am logged in with username \"(.*?)\" and password \"(.*?)\"$")
    public void i_am_logged_in_with_username_and_password(String email, String password) throws Throwable {
        driver.get("http://localhost/login");
        WebElement emailElement = driver.findElementById("email");
        emailElement.sendKeys(email);
        WebElement passwordElement = driver.findElementById("password");
        passwordElement.sendKeys(password);
        driver.findElementById("login").sendKeys(Keys.RETURN);//.click();
    }

    @Given("^I am on the conversion page$")
    public void i_am_on_the_conversion_page() throws Throwable {
        Assert.assertEquals("http://localhost/conversion", driver.getCurrentUrl());
    }

    @And("^I enter an amount of \"(.*?)\"$")
    public void i_enter_an_amount_of(String amount) throws Throwable {
        WebElement amountElement = driver.findElementById("amount");
        amountElement.sendKeys(amount);
        // Write code here that turns the phrase above into concrete actions
    }

    @And("^I choose the base currency \"(.*?)\"$")
    public void i_choose_the_base_currency(String baseCurrency) throws Throwable {
        WebElement baseCurrencyElement = driver.findElementById("baseCurrencyLatest");
        Select select = new Select(baseCurrencyElement);
        //select.deselectAll();
        select.selectByVisibleText(baseCurrency);
        // Write code here that turns the phrase above into concrete actions
    }

    @And("^I choose the target currency \"(.*?)\"$")
    public void i_choose_the_target_currency(String targetCurrency) throws Throwable {
        WebElement baseCurrencyElement = driver.findElementById("targetcurrency");
        Select select = new Select(baseCurrencyElement);
        //select.deselectAll();
        select.selectByVisibleText(targetCurrency);
    }

    @When("^I enter an historical date of \"(.*?)\"$")
    public void i_enter_an_historical_date_of(String historicalDate) throws Throwable {

        WebElement emailElement = driver.findElementById("historyDate");
        driver.getCurrentUrl();//.getCurrentUrl();
        emailElement.sendKeys(historicalDate);
    }

    @Then("^I should have the result of the conversion$")
    public void i_should_have_the_result_of_the_conversion() throws Throwable {
        driver.findElementById("convert").submit();//.sendKeys(Keys.RETURN);//.click();
        driver.findElementById("targetamount").getText();
    }

    /*@Given("^I am on the login page$")
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
    }*/
}
