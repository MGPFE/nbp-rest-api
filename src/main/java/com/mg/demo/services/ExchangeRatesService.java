package com.mg.demo.services;

import com.mg.demo.utils.DateManipulation;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(httpAddress, String.class);

        JSONObject jsonObject = new JSONObject(result);
        jsonObject.remove("table");

        return jsonObject.toMap();
    }
}
