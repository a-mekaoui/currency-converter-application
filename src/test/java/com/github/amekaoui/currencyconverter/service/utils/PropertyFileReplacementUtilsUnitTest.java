package com.github.amekaoui.currencyconverter.service.utils;

import org.junit.Assert;
import org.junit.Test;

public class PropertyFileReplacementUtilsUnitTest {

    @Test
    public void replaceHistoricalUrlProperty() {
        String urlFromProperties = "https://openexchangerates.org/api/historical/year-month-day.json?app_id=1dce92a72141466e8be2a33c736c5743";
        String actualUrl = PropertyFileReplacementUtils.replaceHistoricalUrlProperty(urlFromProperties, "01", "03", "2018");
        String expectedUrl = "https://openexchangerates.org/api/historical/2018-03-01.json?app_id=1dce92a72141466e8be2a33c736c5743";
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}