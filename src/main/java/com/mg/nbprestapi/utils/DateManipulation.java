package com.mg.nbprestapi.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateManipulation {
    public static LocalDate subtractDaysSkipWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int subtractedDays = 0;

        while (subtractedDays < days) {
            result = result.minusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY))
                subtractedDays++;
        }
        return result;
    }
}
