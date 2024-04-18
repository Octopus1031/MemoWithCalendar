package com.example.memobackend;

import java.time.LocalDate;
import java.time.YearMonth;


public class Calendar {
    public String generateCalendarDays(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // 0 = Sunday, 1 = Monday, ..., 6 = Saturday

        StringBuilder calendarHtml = new StringBuilder();

        int day = 1;
        for (int i = 0; i < 6; i++) { // for each week
            for (int j = 0; j < 7; j++) { // for each day
                if (i == 0 && j < dayOfWeek || day > daysInMonth) {
                    calendarHtml.append("<div></div>");
                } else {
                    calendarHtml.append("<div>").append(day++).append("</div>");
                }
            }
            if (day > daysInMonth) {
                break;
            }
        }

        return calendarHtml.toString();
    }
}