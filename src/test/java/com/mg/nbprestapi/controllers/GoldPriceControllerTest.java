package com.mg.nbprestapi.controllers;

import com.mg.nbprestapi.services.GoldPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GoldPriceControllerTest {
    @Mock
    private GoldPriceService goldPriceService;
    private GoldPriceController underTest;
    private final String baseHttpAddress = "http://localhost:8080/api";
    private HttpClient client;

    @BeforeEach
    void setUp() {
        this.client = HttpClient.newHttpClient();
        underTest = new GoldPriceController(this.goldPriceService);
    }

    @Test
    public void shouldReturnStatusCode200() throws URISyntaxException, IOException, InterruptedException {
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
    public void shouldReturnJson() throws URISyntaxException, IOException, InterruptedException {
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
    public void shouldCallGetAverageGoldPriceMethodFromService() {
        // When
        given(goldPriceService.getAverageGoldPrice())
            .willReturn(Map.of());

        underTest.getAverageGoldPrice();

        // Then
        verify(goldPriceService).getAverageGoldPrice();
    }
}
