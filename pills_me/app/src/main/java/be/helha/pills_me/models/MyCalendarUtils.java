package be.helha.pills_me.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyCalendarUtils {

    private static SimpleDateFormat getDateFormat() {
        String patternDateFormat = "d/M/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(patternDateFormat, Locale.FRENCH);
        return sdf;
    }

    public static Date parseStringInDate(String stringDate) {
        try {
            Date date = getDateFormat().parse(stringDate);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseDateInString(Date date) {
        String stringDate = getDateFormat().format(date);
        return stringDate;
    }
}
