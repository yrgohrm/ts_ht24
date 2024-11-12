package se.yrgo;

import java.time.*;

public class DayUtils {
    public static String isItFriday(DayOfWeek dow) {
        if (dow == DayOfWeek.FRIDAY) {
            return "TGIF";
        }

        return "Nope";
    }
}
