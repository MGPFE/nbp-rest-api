package com.mg.nbprestapi.controllers;

import com.mg.nbprestapi.services.ExchangeRatesService;
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
