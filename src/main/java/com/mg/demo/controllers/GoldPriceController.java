package com.mg.demo.controllers;

import com.mg.demo.services.GoldPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gold-price")
public class GoldPriceController {
    private final GoldPriceService service;

    public GoldPriceController(GoldPriceService goldPriceService) {
        this.service = goldPriceService;
    }

    @GetMapping("/average")
    public Map<String, Object> getAverageGoldPrice() {
        return this.service.getAverageGoldPrice();
    }
}
