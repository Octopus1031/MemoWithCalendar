package com.example.memobackend;

import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar {
    int MonthFirstDayWeek;

    public String generateCalendarDays(int year, int month, int day) {
        try {
            if(year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
                throw new Exception();
            }
            if(month==2 && day>29) {
                throw new Exception();
            }
            if((month==4 || month==6 || month==9 || month==11) && day>30) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "Illegal date input!";
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        MonthFirstDayWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // 0 = Sunday, 1 = Monday, ..., 6 = Saturday

        // 取得今天的日期來決定selected_day的class加在哪一天
        int todayMonth = LocalDate.now().getMonthValue();
        int todayYear = LocalDate.now().getYear();

        StringBuilder calendarHtml = new StringBuilder();

        // 暫時在事前處理時僅先將當天的日期加上selected_day的class
        int initDay = 1;
        for (int i = 0; i < 6; i++) { // for each week
            for (int j = 0; j < 7; j++) { // for each day
                if (i == 0 && j < MonthFirstDayWeek || initDay > daysInMonth) {
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

    int getMonthFirstDayWeek() {
        return MonthFirstDayWeek;
    }
}