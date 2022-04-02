package com.mg.demo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class GoldPriceService {
    public static Map<String, Object> getAverageGoldPrice() {
        // http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate current = LocalDate.now();
//        LocalDate
        return Map.of();
    }
}
