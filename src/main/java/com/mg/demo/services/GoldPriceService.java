package com.mg.demo.services;

import com.mg.demo.utils.DateManipulation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class GoldPriceService {
    public Map<String, Object> getAverageGoldPrice() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentBusinessDay = DateManipulation.subtractDaysSkipWeekends(LocalDate.now(), 0);
        LocalDate fourteenBusinessDaysAgo = DateManipulation.subtractDaysSkipWeekends(currentBusinessDay, 14);
        BigDecimal averagePrice = new BigDecimal(0);
        String httpAddress = "http://api.nbp.pl/api/cenyzlota/" + dtf.format(fourteenBusinessDaysAgo)
            + "/" + dtf.format(currentBusinessDay);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(httpAddress, String.class);

        JSONArray jsonArray = new JSONArray(result);
        int arrLength = jsonArray.length();

        for (Object element : jsonArray) {
            JSONObject object = (JSONObject) element;
            double price = Double.parseDouble(object.get("cena").toString());
            averagePrice = averagePrice.add(BigDecimal.valueOf(price));
        }

        averagePrice = averagePrice.divide(BigDecimal.valueOf(arrLength), 2, RoundingMode.HALF_EVEN);

        return Map.of(
            "average_price_last_14_business_days", averagePrice
        );
    }
}
