package com.github.amekaoui.currencyconverter.service.utils;

public class PropertyFileReplacementUtils {
    public static String replaceHistoricalUrlProperty(String historicalRatesUrl, String day, String month, String year) {
        return historicalRatesUrl.replaceAll("day", day).replaceAll("month", month).replaceAll("year", year);
    }
}
