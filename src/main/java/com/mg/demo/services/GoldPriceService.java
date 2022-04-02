package com.mg.demo.services;

import com.mg.demo.utils.DateManipulation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class GoldPriceService {
    private final String baseHttpAddress = "http://api.nbp.pl/api/cenyzlota/";

    public Map<String, Object> getAverageGoldPrice() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        HttpClient client = HttpClient.newHttpClient();
        LocalDate currentBusinessDay = DateManipulation.subtractDaysSkipWeekends(LocalDate.now(), 0);
        LocalDate fourteenBusinessDaysAgo = DateManipulation.subtractDaysSkipWeekends(currentBusinessDay, 14);
        BigDecimal averagePrice = new BigDecimal(0);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseHttpAddress + dtf.format(fourteenBusinessDaysAgo) + "/" + dtf.format(currentBusinessDay)))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArray = new JSONArray(response.body());
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
        } catch (URISyntaxException | InterruptedException | IOException | JSONException e) {
            return Map.of();
        }
    }
}
