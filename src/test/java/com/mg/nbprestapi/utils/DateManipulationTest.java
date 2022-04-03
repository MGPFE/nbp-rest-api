package com.mg.nbprestapi.utils;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateManipulationTest {
    @Test
    public void shouldReturnMondayWhenSundayAndFiveDaysBack() {
        // Given
        LocalDate date = LocalDate.of(2022, 4, 3);

        // When
        LocalDate result = DateManipulation.subtractDaysSkipWeekends(date, 5);

        // Then
        assertThat(result.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    public void shouldReturnMondayWhenSaturdayAndFiveDaysBack() {
        // Given
        LocalDate date = LocalDate.of(2022, 4, 2);

        // When
        LocalDate result = DateManipulation.subtractDaysSkipWeekends(date, 5);

        // Then
        assertThat(result.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    public void shouldReturnFridayWhenFridayAndFiveDaysBack() {
        // Given
        LocalDate date = LocalDate.of(2022, 4, 1);

        // When
        LocalDate result = DateManipulation.subtractDaysSkipWeekends(date, 5);

        // Then
        assertThat(result.getDayOfWeek()).isEqualTo(DayOfWeek.FRIDAY);
    }

    @Test
    public void shouldReturnMondayWhenMondayAndFiveDaysBack() {
        // Given
        LocalDate date = LocalDate.of(2022, 4, 4);

        // When
        LocalDate result = DateManipulation.subtractDaysSkipWeekends(date, 5);

        // Then
        assertThat(result.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }
}