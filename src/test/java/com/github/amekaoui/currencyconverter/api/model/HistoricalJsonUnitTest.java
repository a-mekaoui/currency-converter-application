package com.github.amekaoui.currencyconverter.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.historical.HistoricalRates;
import com.github.amekaoui.currencyconverter.service.converter.HistoricalRateConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class HistoricalJsonUnitTest {
    @Test
    public void mappingJsonToHistoricalRatesShouldNotFailTest() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = this.getJsonResponse();

        try {
            HistoricalRates historicalRates = mapper.readValue(jsonResponse, HistoricalRates.class);
            Map<Currency, Map<Currency, BigDecimal>> retrievedHistoricalRates = HistoricalRateConverter.convertHistoricalRates(historicalRates);
            Map<Currency, BigDecimal> usdHistoryRates = retrievedHistoricalRates.get(Currency.USD);
            Assert.assertEquals(BigDecimal.valueOf(0.832586), usdHistoryRates.get(Currency.EUR));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    private String getJsonResponse() {
        String jsonResponse = "{\n" +
                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                "  \"timestamp\": 1514851199,\n" +
                "  \"base\": \"USD\",\n" +
                "  \"rates\": {\n" +
                "    \"AED\": 3.672779,\n" +
                "    \"AFN\": 69.5395,\n" +
                "    \"ALL\": 110.661801,\n" +
                "    \"AMD\": 483.164905,\n" +
                "    \"ANG\": 1.780934,\n" +
                "    \"AOA\": 165.9235,\n" +
                "    \"ARS\": 18.591408,\n" +
                "    \"AUD\": 1.282205,\n" +
                "    \"AWG\": 1.789996,\n" +
                "    \"AZN\": 1.7,\n" +
                "    \"BAM\": 1.62925,\n" +
                "    \"BBD\": 2,\n" +
                "    \"BDT\": 82.615,\n" +
                "    \"BGN\": 1.62956,\n" +
                "    \"BHD\": 0.376209,\n" +
                "    \"BIF\": 1754.263054,\n" +
                "    \"BMD\": 1,\n" +
                "    \"BND\": 1.336985,\n" +
                "    \"BOB\": 6.919158,\n" +
                "    \"BRL\": 3.31251,\n" +
                "    \"BSD\": 1,\n" +
                "    \"BTC\": 0.00007443417,\n" +
                "    \"BTN\": 63.72543,\n" +
                "    \"BWP\": 9.806005,\n" +
                "    \"BYN\": 1.974791,\n" +
                "    \"BZD\": 2.007066,\n" +
                "    \"CAD\": 1.25567,\n" +
                "    \"CDF\": 1562.881563,\n" +
                "    \"CHF\": 0.974801,\n" +
                "    \"CLF\": 0.02305,\n" +
                "    \"CLP\": 613.49,\n" +
                "    \"CNH\": 6.517,\n" +
                "    \"CNY\": 6.52085,\n" +
                "    \"COP\": 2977.65,\n" +
                "    \"CRC\": 565.137717,\n" +
                "    \"CUC\": 1,\n" +
                "    \"CUP\": 25.5,\n" +
                "    \"CVE\": 92.2,\n" +
                "    \"CZK\": 21.274775,\n" +
                "    \"DJF\": 178.57,\n" +
                "    \"DKK\": 6.19814,\n" +
                "    \"DOP\": 48.284168,\n" +
                "    \"DZD\": 114.764,\n" +
                "    \"EGP\": 17.7605,\n" +
                "    \"ERN\": 15.33,\n" +
                "    \"ETB\": 27.361693,\n" +
                "    \"EUR\": 0.832586,\n" +
                "    \"FJD\": 2.037954,\n" +
                "    \"FKP\": 0.740188,\n" +
                "    \"GBP\": 0.740188,\n" +
                "    \"GEL\": 2.591663,\n" +
                "    \"GGP\": 0.740188,\n" +
                "    \"GHS\": 4.526409,\n" +
                "    \"GIP\": 0.740188,\n" +
                "    \"GMD\": 47.6625,\n" +
                "    \"GNF\": 9007.45,\n" +
                "    \"GTQ\": 7.333271,\n" +
                "    \"GYD\": 206.48,\n" +
                "    \"HKD\": 7.814332,\n" +
                "    \"HNL\": 23.601022,\n" +
                "    \"HRK\": 6.193,\n" +
                "    \"HTG\": 63.765786,\n" +
                "    \"HUF\": 258.51,\n" +
                "    \"IDR\": 13563.447201,\n" +
                "    \"ILS\": 3.47575,\n" +
                "    \"IMP\": 0.740188,\n" +
                "    \"INR\": 63.8802,\n" +
                "    \"IQD\": 1190.225723,\n" +
                "    \"IRR\": 35628.917176,\n" +
                "    \"ISK\": 103.426806,\n" +
                "    \"JEP\": 0.740188,\n" +
                "    \"JMD\": 124.318143,\n" +
                "    \"JOD\": 0.710507,\n" +
                "    \"JPY\": 112.7745,\n" +
                "    \"KES\": 103.05798,\n" +
                "    \"KGS\": 69.456,\n" +
                "    \"KHR\": 4055.391667,\n" +
                "    \"KMF\": 410.664675,\n" +
                "    \"KPW\": 900,\n" +
                "    \"KRW\": 1066.25,\n" +
                "    \"KWD\": 0.301321,\n" +
                "    \"KYD\": 0.831451,\n" +
                "    \"KZT\": 332.140291,\n" +
                "    \"LAK\": 8299.95,\n" +
                "    \"LBP\": 1508.537443,\n" +
                "    \"LKR\": 153.14,\n" +
                "    \"LRD\": 125.481528,\n" +
                "    \"LSL\": 12.27671,\n" +
                "    \"LYD\": 1.357406,\n" +
                "    \"MAD\": 9.332579,\n" +
                "    \"MDL\": 17.075884,\n" +
                "    \"MGA\": 3259.834946,\n" +
                "    \"MKD\": 51.285,\n" +
                "    \"MMK\": 1351.598174,\n" +
                "    \"MNT\": 2422.886667,\n" +
                "    \"MOP\": 8.037409,\n" +
                "    \"MRO\": 354.278571,\n" +
                "    \"MRU\": 35.1,\n" +
                "    \"MUR\": 33.532909,\n" +
                "    \"MVR\": 15.400251,\n" +
                "    \"MWK\": 724.597867,\n" +
                "    \"MXN\": 19.6582,\n" +
                "    \"MYR\": 4.0614,\n" +
                "    \"MZN\": 59.002548,\n" +
                "    \"NAD\": 12.275933,\n" +
                "    \"NGN\": 358.519239,\n" +
                "    \"NIO\": 30.74599,\n" +
                "    \"NOK\": 8.19704,\n" +
                "    \"NPR\": 102.02656,\n" +
                "    \"NZD\": 1.411234,\n" +
                "    \"OMR\": 0.385,\n" +
                "    \"PAB\": 1,\n" +
                "    \"PEN\": 3.237502,\n" +
                "    \"PGK\": 3.210029,\n" +
                "    \"PHP\": 49.92,\n" +
                "    \"PKR\": 110.415774,\n" +
                "    \"PLN\": 3.47799,\n" +
                "    \"PYG\": 5577.8,\n" +
                "    \"QAR\": 3.63155,\n" +
                "    \"RON\": 3.887501,\n" +
                "    \"RSD\": 98.424,\n" +
                "    \"RUB\": 57.618,\n" +
                "    \"RWF\": 857.653211,\n" +
                "    \"SAR\": 3.7507,\n" +
                "    \"SBD\": 7.762342,\n" +
                "    \"SCR\": 14.0335,\n" +
                "    \"SDG\": 6.997729,\n" +
                "    \"SEK\": 8.181884,\n" +
                "    \"SGD\": 1.337503,\n" +
                "    \"SHP\": 0.740188,\n" +
                "    \"SLL\": 7651.25,\n" +
                "    \"SOS\": 576.818119,\n" +
                "    \"SRD\": 7.458,\n" +
                "    \"SSP\": 130.2634,\n" +
                "    \"STD\": 20428.755458,\n" +
                "    \"STN\": 20.428755,\n" +
                "    \"SVC\": 8.731329,\n" +
                "    \"SYP\": 515.05499,\n" +
                "    \"SZL\": 12.283251,\n" +
                "    \"THB\": 32.57,\n" +
                "    \"TJS\": 8.80575,\n" +
                "    \"TMT\": 3.509974,\n" +
                "    \"TND\": 2.455502,\n" +
                "    \"TOP\": 2.26375,\n" +
                "    \"TRY\": 3.792194,\n" +
                "    \"TTD\": 6.71525,\n" +
                "    \"TWD\": 29.785,\n" +
                "    \"TZS\": 2246.237374,\n" +
                "    \"UAH\": 28.113166,\n" +
                "    \"UGX\": 3633.608069,\n" +
                "    \"USD\": 1,\n" +
                "    \"UYU\": 28.827063,\n" +
                "    \"UZS\": 8101.4,\n" +
                "    \"VEF\": 9.985022,\n" +
                "    \"VND\": 22700.883864,\n" +
                "    \"VUV\": 105.346667,\n" +
                "    \"WST\": 2.5337,\n" +
                "    \"XAF\": 546.140618,\n" +
                "    \"XAG\": 0.0587683,\n" +
                "    \"XAU\": 0.0007652,\n" +
                "    \"XCD\": 2.70255,\n" +
                "    \"XDR\": 0.702181,\n" +
                "    \"XOF\": 546.140618,\n" +
                "    \"XPD\": 0.00093548,\n" +
                "    \"XPF\": 99.353939,\n" +
                "    \"XPT\": 0.00107025,\n" +
                "    \"YER\": 250.321094,\n" +
                "    \"ZAR\": 12.401783,\n" +
                "    \"ZMW\": 9.977289,\n" +
                "    \"ZWL\": 322.355011\n" +
                "  }\n" +
                "}";
        return jsonResponse;
    }
}
