package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

        result = calendar.generateCalendarDays(2024, 6, 31);
        assertEquals("Illegal date input!", result);
    }
}