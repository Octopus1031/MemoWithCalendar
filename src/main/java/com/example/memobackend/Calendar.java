package com.example.memobackend;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class Calendar {
    public String generateCalendarDays(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // 0 = Sunday, 1 = Monday, ..., 6 = Saturday

        StringBuilder calendarHtml = new StringBuilder();
        //calendarHtml.append("<table>\n");
        //calendarHtml.append("<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>\n");

        int day = 1;
        for (int i = 0; i < 6; i++) { // for each week
            //calendarHtml.append("<tr>");
            for (int j = 0; j < 7; j++) { // for each day
                if (i == 0 && j < dayOfWeek || day > daysInMonth) {
                    //calendarHtml.append("<td></td>");
                    calendarHtml.append("<div></div>");
                } else {
                    calendarHtml.append("<div>").append(day++).append("</div>");
                }
            }
            //calendarHtml.append("</tr>\n");
            if (day > daysInMonth) {
                break;
            }
        }

        //calendarHtml.append("</table>\n");
        return calendarHtml.toString();
    }
}