package com.yusufsoysal.strava.utils;

import java.time.LocalDateTime;

public class DateUtils {
    private static final LocalDateTime minimumDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

    public static LocalDateTime minimumDate() {
        return minimumDate;
    }

}
