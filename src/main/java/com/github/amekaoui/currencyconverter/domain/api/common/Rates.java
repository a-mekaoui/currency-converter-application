package com.github.amekaoui.currencyconverter.domain.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    @JsonProperty("AED")
    private BigDecimal AED;

    @JsonProperty("AUD")
    private BigDecimal AUD;

    @JsonProperty("EUR")
    private BigDecimal EUR;

    @JsonProperty("USD")
    private BigDecimal USD;

    @JsonProperty("GBP")
    private BigDecimal GBP;

    @JsonProperty("NZD")
    private BigDecimal NZD;

    @JsonProperty("JPY")
    private BigDecimal JPY;

    public BigDecimal getAUD() {
        return AUD;
    }

    public void setAUD(BigDecimal AUD) {
        this.AUD = AUD;
    }

    public BigDecimal getEUR() {
        return EUR;
    }

    public void setEUR(BigDecimal EUR) {
        this.EUR = EUR;
    }

    public BigDecimal getUSD() {
        return USD;
    }

    public void setUSD(BigDecimal USD) {
        this.USD = USD;
    }

    public BigDecimal getGBP() {
        return GBP;
    }

    public void setGBP(BigDecimal GBP) {
        this.GBP = GBP;
    }

    public BigDecimal getNZD() {
        return NZD;
    }

    public void setNZD(BigDecimal NZD) {
        this.NZD = NZD;
    }

    public BigDecimal getAED() {
        return AED;
    }

    public void setAED(BigDecimal AED) {
        this.AED = AED;
    }

    public BigDecimal getJPY() {
        return JPY;
    }

    public void setJPY(BigDecimal JPY) {
        this.JPY = JPY;
    }
}