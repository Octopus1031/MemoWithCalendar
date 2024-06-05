package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CalendarTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateCalendarDays() {
        Calendar calendar = new Calendar();
        String result = calendar.generateCalendarDays(2024, 4, 25);
        assertNotNull(result);
        assertEquals(1, calendar.getMonthFirstDayWeek());

        result = calendar.generateCalendarDays(2024, 5, 25);
        assertNotNull(result);
        assertEquals(3, calendar.getMonthFirstDayWeek());

        result = calendar.generateCalendarDays(2024, 3, 25);
        assertNotNull(result);
        assertEquals(5, calendar.getMonthFirstDayWeek());
    }

    @Test
    public void generateCalendarDaysIllegalDate() {
        Calendar calendar = new Calendar();
        String result = calendar.generateCalendarDays(2024, 4, 31);
        assertEquals("Illegal date input!", result);

        result = calendar.generateCalendarDays(2024, 2, 30);
        assertEquals("Illegal date input!", result);

        result = calendar.generateCalendarDays(2024, -1, 31);
        assertEquals("Illegal date input!", result);
    }

    @Test
    public void getMonthHighlightDays(){
        MemoList memoList = new MemoList();
        memoList.addMemoItem(new MemoItem("title", "2024/06/05 12:00", "5 minutes ago", "description", 1L));
        memoList.addMemoItem(new MemoItem("title", "2024/06/10 12:00", "5 minutes ago", "description", 2L));
        memoList.addMemoItem(new MemoItem("title", "2024/06/15 12:00", "5 minutes ago", "description", 3L));
        memoList.addMemoItem(new MemoItem("title", "2024/07/15 12:00", "5 minutes ago", "description", 4L));
        memoList.addMemoItem(new MemoItem("title", "2024/05/15 12:00", "5 minutes ago", "description", 5L));

        Calendar calendar = memoList.getCalendar();
        // 設定正在顯示的月份
//        calendar.generateCalendarDays(2024, 6, 1);
        calendar.setShowingYear(2024);
        calendar.setShowingMonth(6);
        List<Integer> result = calendar.getMonthHighlightDays();
        assertEquals(3, result.size());
        assertEquals(5, result.get(0).intValue());
        assertEquals(10, result.get(1).intValue());
        assertEquals(15, result.get(2).intValue());
    }

}