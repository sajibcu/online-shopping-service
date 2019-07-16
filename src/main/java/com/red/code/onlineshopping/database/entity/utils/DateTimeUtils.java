package com.red.code.onlineshopping.database.entity.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;


public final class DateTimeUtils {

    private DateTimeUtils() {
    }

    /**
     * @param localDateTime
     * @param dayOfMonth
     * @return
     */
    public static boolean isValidDayOfMonth(LocalDateTime localDateTime, int dayOfMonth) {
        try {
            localDateTime.withDayOfMonth(dayOfMonth);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}
