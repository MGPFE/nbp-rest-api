package com.mg.nbprestapi.services;

import org.springframework.web.client.RestTemplate;
import com.mg.nbprestapi.utils.DateManipulation;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Map;

@Service
public class ExchangeRatesService {
    public Map<String, Object> getExchangeRate(String code) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentBusinessDay = DateManipulation
            .subtractDaysSkipWeekends(LocalDate.now(), 0);
        LocalDate fiveBusinessDaysAgo = DateManipulation
            .subtractDaysSkipWeekends(currentBusinessDay, 5);
        String httpAddress = "http://api.nbp.pl/api/exchangerates/rates/A/" + code + "/"
            + dtf.format(fiveBusinessDaysAgo) + "/" + dtf.format(currentBusinessDay);

        // These two lines of code are retrieving the results from NBP API
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(httpAddress, String.class);

        JSONObject jsonObject = new JSONObject(result);
        // Removes the unnecessary table type info
        jsonObject.remove("table");

        return jsonObject.toMap();
    }
}
