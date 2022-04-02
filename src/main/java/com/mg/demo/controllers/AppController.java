package com.mg.demo.controllers;

import com.mg.demo.services.ExchangeRatesService;
import com.mg.demo.services.GoldPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppController {
    @GetMapping("/exchange-rates/{code}")
    public Map<String, Object> exchangeRates(@PathVariable String code) {
        return ExchangeRatesService.getExchangeRate(code);
    }

    @GetMapping("/gold-price/average")
    public Map<String, Object> goldPrice() {
        return GoldPriceService.getAverageGoldPrice();
    }
}
