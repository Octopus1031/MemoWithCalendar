package com.example.memobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CalendarController {
    @Autowired
    private MemoList memoList;
    private Calendar calendar;

    public CalendarController(MemoList memoList) {
        this.memoList = memoList;
        this.calendar = memoList.getCalendar();
    }

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam int year, @RequestParam int month, @RequestParam int day) {
        return calendar.generateCalendarDays(year, month, day);
    }

    @GetMapping("refresh_highlight")
    public List<Integer> refresh_highlight() {
        List<Integer> eventDays = calendar.getMonthHighlightDays();
        System.out.println("eventDays: l=" + eventDays.size() + " ");
        for (Integer eventDay : eventDays) {
            System.out.print(eventDay+" ");
        }
        System.out.println();
        return eventDays;
    }
}