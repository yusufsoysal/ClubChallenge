package com.yusufsoysal.strava.utils;

public class Utilities {
    public static <T extends Enum<T>> T valueOf(Class<T> enumType, Enum stravaEnum) {
        if (stravaEnum == null) {
            return null;
        }
        return Enum.valueOf(enumType, stravaEnum.name());
    }
}
