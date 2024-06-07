package com.example.memobackend;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


public class Calendar {
    private int MonthFirstDayWeek;
    private int showingYear, showingMonth;
    private MemoList memoList;

    public Calendar() {
    }

    public Calendar( MemoList memoList) {
        this.memoList = memoList;
    }

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
        // 儲存正在顯示的年月以做為後續更新日曆判別是否加上event highlight的參數
        setShowingYear(year);
        setShowingMonth(month);
        System.out.println("update showingYear: " + showingYear + ", showingMonth: " + showingMonth);
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
            String htmlCode_event = "";
            for (int j = 0; j < 7; j++) { // for each day
                if (i == 0 && j < MonthFirstDayWeek || initDay > daysInMonth) {
                    calendarHtml.append("<div></div>");
                    htmlCode_event += "<div class='no_event'></div>";
                } else {
                    String htmlCode;
                    if(initDay == day && todayMonth == month && todayYear == year) {
                        htmlCode = "<div class='selected_day' id='d_" + initDay + "' onclick='selectDay()'>";
                    } else {
                        htmlCode = "<div class='unselected_day' id='d_" + initDay + "' onclick='selectDay()'>";
                    }
                    calendarHtml.append(htmlCode).append(initDay).append("</div>");

                    htmlCode_event += "<div class='no_event' id='e_" + initDay + "'></div>";

                    initDay++;
                }
            }
            calendarHtml.append(htmlCode_event);

            if (initDay > daysInMonth) {
                break;
            }
        }

        return calendarHtml.toString();
    }

    int getMonthFirstDayWeek() {
        return MonthFirstDayWeek;
    }

    // 取得當年月的所有有event的日期
    public List<Integer> getMonthHighlightDays() {
        List<Integer> monthHighlightDays = new ArrayList<>();
        List<Integer> allMemoItemDay = memoList.getAllMemoItemParseDayFormatToInt();
        System.out.println("memoList.getAllMemoItemDay().size(): " + allMemoItemDay.size());
        for (Integer i : allMemoItemDay) {
            int year = i / 10000;
            if (year == showingYear) {
                int month = (i % 10000) / 100;
                if (month == showingMonth) {
                    monthHighlightDays.add(i % 100);
                }
            }
        }
        System.out.println("showingYear: " + showingYear + ", showingMonth: " + showingMonth);
        return monthHighlightDays;
    }

    public void setShowingYear(int showingYear) {
        this.showingYear = showingYear;
    }

    public void setShowingMonth(int showingMonth) {
        this.showingMonth = showingMonth;
    }
}