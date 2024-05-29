package com.toolrental.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to manage holidays.
 */
public class HolidayManager {
    private static HolidayManager instance;
    private Set<LocalDate> holidays;

    private HolidayManager() {
        holidays = new HashSet<>();
        // Add holidays for a range of years
        for (int year = 2000; year <= 2100; year++) {
            holidays.add(calculateIndependenceDay(year));
            holidays.add(calculateLaborDay(year));
        }
    }

    public static HolidayManager getInstance() {
        if (instance == null) {
            instance = new HolidayManager();
        }
        return instance;
    }

    /**
     * Checks if a given date is a holiday.
     * @param date The date to check.
     * @return True if the date is a holiday, false otherwise.
     */
    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    /**
     * Calculates the observed date for Independence Day.
     * @param year The year.
     * @return The observed date for Independence Day.
     */
    private LocalDate calculateIndependenceDay(int year) {
        LocalDate date = LocalDate.of(year, 7, 4);
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.minusDays(1);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date.plusDays(1);
        }
        return date;
    }

    /**
     * Calculates the date for Labor Day (first Monday in September).
     * @param year The year.
     * @return The date for Labor Day.
     */
    private LocalDate calculateLaborDay(int year) {
        LocalDate date = LocalDate.of(year, 9, 1);
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.plusDays(1);
        }
        return date;
    }
}
