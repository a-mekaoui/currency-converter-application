package com.github.amekaoui.currencyconverter.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.amekaoui.currencyconverter.domain.api.Currency;
import com.github.amekaoui.currencyconverter.domain.api.latest.LatestRates;
import com.github.amekaoui.currencyconverter.service.converter.LatestRateConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class LatestJsonUnitTest {
    @Test
    public void mappingJsonToLatestRatesShouldNotFailTest() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = this.getJsonResponse();

        try {
            LatestRates latestRates = mapper.readValue(jsonResponse, LatestRates.class);
            Map<Currency, Map<Currency, BigDecimal>> retrievedLatestRates = LatestRateConverter.convertLatestRates(latestRates);
            Map<Currency, BigDecimal> usdLatestRates = retrievedLatestRates.get(Currency.USD);
            Assert.assertEquals(BigDecimal.valueOf(0.809389), usdLatestRates.get(Currency.EUR));


        } catch (IOException e) {
            Assert.fail();
        }
    }

    private String getJsonResponse() {
        String jsonResponse = "{\n" +
                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                "  \"timestamp\": 1521894923,\n" +
                "  \"base\": \"USD\",\n" +
                "  \"rates\": {\n" +
                "    \"AED\": 3.673097,\n" +
                "    \"AFN\": 69.375,\n" +
                "    \"ALL\": 105.2,\n" +
                "    \"AMD\": 480,\n" +
                "    \"ANG\": 1.783141,\n" +
                "    \"AOA\": 214.584,\n" +
                "    \"ARS\": 20.1755,\n" +
                "    \"AUD\": 1.299545,\n" +
                "    \"AWG\": 1.784998,\n" +
                "    \"AZN\": 1.7025,\n" +
                "    \"BAM\": 1.582704,\n" +
                "    \"BBD\": 2,\n" +
                "    \"BDT\": 82.940945,\n" +
                "    \"BGN\": 1.582263,\n" +
                "    \"BHD\": 0.37699,\n" +
                "    \"BIF\": 1758.45,\n" +
                "    \"BMD\": 1,\n" +
                "    \"BND\": 1.313695,\n" +
                "    \"BOB\": 6.902739,\n" +
                "    \"BRL\": 3.311467,\n" +
                "    \"BSD\": 1,\n" +
                "    \"BTC\": 0.000114080381,\n" +
                "    \"BTN\": 64.944951,\n" +
                "    \"BWP\": 9.546371,\n" +
                "    \"BYN\": 1.947467,\n" +
                "    \"BZD\": 2.007935,\n" +
                "    \"CAD\": 1.28945,\n" +
                "    \"CDF\": 1615,\n" +
                "    \"CHF\": 0.946925,\n" +
                "    \"CLF\": 0.0227,\n" +
                "    \"CLP\": 608.4,\n" +
                "    \"CNH\": 6.319657,\n" +
                "    \"CNY\": 6.3114,\n" +
                "    \"COP\": 2856.95,\n" +
                "    \"CRC\": 566.3,\n" +
                "    \"CUC\": 1,\n" +
                "    \"CUP\": 25.5,\n" +
                "    \"CVE\": 89.66,\n" +
                "    \"CZK\": 20.5801,\n" +
                "    \"DJF\": 177.075,\n" +
                "    \"DKK\": 6.0293,\n" +
                "    \"DOP\": 49.488573,\n" +
                "    \"DZD\": 113.985,\n" +
                "    \"EGP\": 17.619,\n" +
                "    \"ERN\": 15.093333,\n" +
                "    \"ETB\": 27.390127,\n" +
                "    \"EUR\": 0.809389,\n" +
                "    \"FJD\": 2.028056,\n" +
                "    \"FKP\": 0.707489,\n" +
                "    \"GBP\": 0.707489,\n" +
                "    \"GEL\": 2.441002,\n" +
                "    \"GGP\": 0.707489,\n" +
                "    \"GHS\": 4.412611,\n" +
                "    \"GIP\": 0.707489,\n" +
                "    \"GMD\": 47.45,\n" +
                "    \"GNF\": 9005,\n" +
                "    \"GTQ\": 7.407176,\n" +
                "    \"GYD\": 206.845,\n" +
                "    \"HKD\": 7.8465,\n" +
                "    \"HNL\": 23.658603,\n" +
                "    \"HRK\": 6.0178,\n" +
                "    \"HTG\": 64.719141,\n" +
                "    \"HUF\": 253.3,\n" +
                "    \"IDR\": 13781.313762,\n" +
                "    \"ILS\": 3.4862,\n" +
                "    \"IMP\": 0.707489,\n" +
                "    \"INR\": 64.9905,\n" +
                "    \"IQD\": 1191.95,\n" +
                "    \"IRR\": 37571.52369,\n" +
                "    \"ISK\": 98.610096,\n" +
                "    \"JEP\": 0.707489,\n" +
                "    \"JMD\": 126.436809,\n" +
                "    \"JOD\": 0.709503,\n" +
                "    \"JPY\": 104.735,\n" +
                "    \"KES\": 100.922742,\n" +
                "    \"KGS\": 68.157599,\n" +
                "    \"KHR\": 3995,\n" +
                "    \"KMF\": 398.4,\n" +
                "    \"KPW\": 900,\n" +
                "    \"KRW\": 1083.87,\n" +
                "    \"KWD\": 0.299533,\n" +
                "    \"KYD\": 0.832519,\n" +
                "    \"KZT\": 320.517996,\n" +
                "    \"LAK\": 8280.8,\n" +
                "    \"LBP\": 1513.9,\n" +
                "    \"LKR\": 155.959879,\n" +
                "    \"LRD\": 131.3,\n" +
                "    \"LSL\": 11.775275,\n" +
                "    \"LYD\": 1.326711,\n" +
                "    \"MAD\": 9.167717,\n" +
                "    \"MDL\": 16.5035,\n" +
                "    \"MGA\": 3212.15,\n" +
                "    \"MKD\": 49.8215,\n" +
                "    \"MMK\": 1337.6,\n" +
                "    \"MNT\": 2394.332694,\n" +
                "    \"MOP\": 8.075176,\n" +
                "    \"MRO\": 356,\n" +
                "    \"MRU\": 35.375,\n" +
                "    \"MUR\": 33.05,\n" +
                "    \"MVR\": 15.450233,\n" +
                "    \"MWK\": 725,\n" +
                "    \"MXN\": 18.5262,\n" +
                "    \"MYR\": 3.917499,\n" +
                "    \"MZN\": 62,\n" +
                "    \"NAD\": 11.755,\n" +
                "    \"NGN\": 359.62,\n" +
                "    \"NIO\": 31.17,\n" +
                "    \"NOK\": 7.7563,\n" +
                "    \"NPR\": 103.904315,\n" +
                "    \"NZD\": 1.382744,\n" +
                "    \"OMR\": 0.385005,\n" +
                "    \"PAB\": 1,\n" +
                "    \"PEN\": 3.23025,\n" +
                "    \"PGK\": 3.250562,\n" +
                "    \"PHP\": 52.39625,\n" +
                "    \"PKR\": 115.48192,\n" +
                "    \"PLN\": 3.42305,\n" +
                "    \"PYG\": 5554.1,\n" +
                "    \"QAR\": 3.641481,\n" +
                "    \"RON\": 3.774473,\n" +
                "    \"RSD\": 95.8,\n" +
                "    \"RUB\": 57.2334,\n" +
                "    \"RWF\": 864.05,\n" +
                "    \"SAR\": 3.75005,\n" +
                "    \"SBD\": 7.758284,\n" +
                "    \"SCR\": 13.838,\n" +
                "    \"SDG\": 18.142411,\n" +
                "    \"SEK\": 8.2314,\n" +
                "    \"SGD\": 1.3154,\n" +
                "    \"SHP\": 0.707489,\n" +
                "    \"SLL\": 7664.007735,\n" +
                "    \"SOS\": 577.87,\n" +
                "    \"SRD\": 7.468,\n" +
                "    \"SSP\": 130.2634,\n" +
                "    \"STD\": 19872.30957,\n" +
                "    \"STN\": 19.92,\n" +
                "    \"SVC\": 8.741315,\n" +
                "    \"SYP\": 514.98999,\n" +
                "    \"SZL\": 11.771046,\n" +
                "    \"THB\": 31.162383,\n" +
                "    \"TJS\": 8.817898,\n" +
                "    \"TMT\": 3.504988,\n" +
                "    \"TND\": 2.386302,\n" +
                "    \"TOP\": 2.228682,\n" +
                "    \"TRY\": 3.984202,\n" +
                "    \"TTD\": 6.722898,\n" +
                "    \"TWD\": 29.191,\n" +
                "    \"TZS\": 2259.25,\n" +
                "    \"UAH\": 26.238083,\n" +
                "    \"UGX\": 3664.2,\n" +
                "    \"USD\": 1,\n" +
                "    \"UYU\": 28.454164,\n" +
                "    \"UZS\": 8131.45,\n" +
                "    \"VEF\": 40241.5117,\n" +
                "    \"VND\": 22791.432006,\n" +
                "    \"VUV\": 105.555498,\n" +
                "    \"WST\": 2.530905,\n" +
                "    \"XAF\": 530.924326,\n" +
                "    \"XAG\": 0.06038714,\n" +
                "    \"XAU\": 0.0007423,\n" +
                "    \"XCD\": 2.70255,\n" +
                "    \"XDR\": 0.686991,\n" +
                "    \"XOF\": 530.924326,\n" +
                "    \"XPD\": 0.00102337,\n" +
                "    \"XPF\": 96.58579,\n" +
                "    \"XPT\": 0.00105319,\n" +
                "    \"YER\": 250.275,\n" +
                "    \"ZAR\": 11.7375,\n" +
                "    \"ZMW\": 9.479897,\n" +
                "    \"ZWL\": 322.355011\n" +
                "  }\n" +
                "}";
        return jsonResponse;
    }
}
