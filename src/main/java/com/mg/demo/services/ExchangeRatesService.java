package com.mg.demo.services;

import com.mg.demo.utils.DateManipulation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ExchangeRatesService {
    // http://api.nbp.pl/api/
    // http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
    public static Map<String, Object> getExchangeRate(String code) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        HttpClient client = HttpClient.newHttpClient();
        LocalDate currentBusinessDay = DateManipulation
            .subtractDaysSkipWeekends(LocalDate.now(), 0);
        LocalDate fiveBusinessDaysAgo = DateManipulation
            .subtractDaysSkipWeekends(currentBusinessDay, 5);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.nbp.pl/api/exchangerates/rates/A/"
                    + code + "/" + dtf.format(fiveBusinessDaysAgo) + "/" + dtf.format(currentBusinessDay) + "/"))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            jsonObject.remove("table");

            return jsonObject.toMap();

        } catch (URISyntaxException | InterruptedException | IOException | JSONException e) {
            return Map.of();
        }
    }
}
