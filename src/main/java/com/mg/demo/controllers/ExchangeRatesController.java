package com.mg.demo.controllers;

import com.mg.demo.services.ExchangeRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRatesController {
    private final ExchangeRatesService service;

    public ExchangeRatesController(ExchangeRatesService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    public Map<String, Object> getExchangeRate(@PathVariable String code) {
        return this.service.getExchangeRate(code);
    }
}
