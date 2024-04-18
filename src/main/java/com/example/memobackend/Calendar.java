package com.example.memobackend;

import java.time.LocalDate;
import java.time.YearMonth;


public class Calendar {

    public String generateCalendarDays(int year, int month, int day) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // 0 = Sunday, 1 = Monday, ..., 6 = Saturday

        // 取得今天的日期來決定selected_day的class加在哪一天
        int todayMonth = LocalDate.now().getMonthValue();
        int todayYear = LocalDate.now().getYear();

        StringBuilder calendarHtml = new StringBuilder();

        // 暫時在事前處理時僅先將當天的日期加上selected_day的class
        int initDay = 1;
        for (int i = 0; i < 6; i++) { // for each week
            for (int j = 0; j < 7; j++) { // for each day
                if (i == 0 && j < dayOfWeek || initDay > daysInMonth) {
                    calendarHtml.append("<div></div>");
                } else {
                    String htmlCode;
                    if(initDay == day && todayMonth == month && todayYear == year) {
                        htmlCode = "<div class='selected_day' id='d_" + initDay + "' onclick='selectDay()'>";
                    } else {
                        htmlCode = "<div id='d_" + initDay + "' onclick='selectDay()'>";
                    }
                    calendarHtml.append(htmlCode).append(initDay).append("</div>");
                    initDay++;
                }
            }
            if (initDay > daysInMonth) {
                break;
            }
        }

        return calendarHtml.toString();
    }
}