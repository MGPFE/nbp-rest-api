package com.mg.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AppControllerTest {
    private final String baseHttpAddress = "http://localhost:8080/api";
    private HttpClient client;

    @BeforeEach
    void setUp() {
        client = HttpClient.newHttpClient();
    }

    @Test
    public void exchangeRatesReturn200() throws URISyntaxException, IOException, InterruptedException {
        // Given
        int desiredCode = 200;
        String urlAppend = "/exchange-rates/1";

        // When
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(baseHttpAddress + urlAppend))
            .GET()
            .build();
        int code = client.send(request, HttpResponse.BodyHandlers.ofString())
            .statusCode();

        // Then
        assertThat(code).isEqualTo(desiredCode);
    }

    @Test
    public void goldPriceAverageReturns200() throws URISyntaxException, IOException, InterruptedException {
        // Given
        int desiredCode = 200;
        String urlAppend = "/gold-price/average";

        // When
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(baseHttpAddress + urlAppend))
            .GET()
            .build();
        int code = client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();

        // Then
        assertThat(code).isEqualTo(desiredCode);
    }

    @Test
    public void exchangeRatesReturnJson() throws URISyntaxException, IOException, InterruptedException {
        // Given
        String desiredContentType = "application/json";
        String urlAppend = "/exchange-rates/1";

        // When
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(baseHttpAddress + urlAppend))
            .GET()
            .build();

        String contentType = client.send(request, HttpResponse.BodyHandlers.ofString())
            .headers()
            .map()
            .get("content-type")
            .get(0)
            .split(";")[0];

        // Then
        assertThat(contentType).isEqualTo(desiredContentType);
    }

    @Test
    public void goldPriceAverageReturnsJson() throws URISyntaxException, IOException, InterruptedException {
        // Given
        String desiredContentType = "application/json";
        String urlAppend = "/gold-price/average";

        // When
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(baseHttpAddress + urlAppend))
            .GET()
            .build();

        String contentType = client.send(request, HttpResponse.BodyHandlers.ofString())
            .headers()
            .map()
            .get("content-type")
            .get(0)
            .split(";")[0];

        // Then
        assertThat(contentType).isEqualTo(desiredContentType);
    }

    @Test
    public void exchangeRatesReturn404WhenNoArgumentPassed() throws URISyntaxException, IOException, InterruptedException {
        // Given
        int desiredCode = 404;
        String urlAppend = "/exchange-rates/";

        // When
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(baseHttpAddress + urlAppend))
            .GET()
            .build();

        int code = client.send(request, HttpResponse.BodyHandlers.ofString())
            .statusCode();

        // Then
        assertThat(code).isEqualTo(desiredCode);
    }
}
