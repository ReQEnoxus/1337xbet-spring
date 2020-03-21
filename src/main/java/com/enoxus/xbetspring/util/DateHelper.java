package com.enoxus.xbetspring.util;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateHelper {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static List<String> getThisWeek() {
        List<String> dates = new ArrayList<>();
        String today = formatter.format(new Date());
        dates.add(today);
        for (int i = 1; i < 8; i++) {
            try {
                dates.add(formatter.format(new Date(formatter.parse(today).getTime() + i * 86400000)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return dates;
    }

    public static String format(Date date) {
        return formatter.format(date);
    }

    @SneakyThrows
    public static Date parse(String date) {
        return formatter.parse(date);
    }

    public static String russianLocalized(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM - HH:mm МСК", new Locale("ru"));
        return sdf.format(date);
    }

    public static String timeLocalized(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm МСК", new Locale("ru"));
        return sdf.format(date);
    }

    public static List<String> thisWeekLocalized() {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM", new Locale("ru"));
        List<String> thisWeek = getThisWeek();
        List<String> localized = new ArrayList<>();
        for (String date : thisWeek) {
            try {
                Date dateRaw = formatter.parse(date);
                localized.add(sdf.format(dateRaw));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return localized;
    }
}
