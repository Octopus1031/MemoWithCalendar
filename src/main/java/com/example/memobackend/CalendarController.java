package com.example.memobackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO 把controller合併 不要多個controller
@RestController
public class CalendarController {
    private final Calendar calendar = new Calendar();

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam int year, @RequestParam int month) {
        return calendar.generateCalendarDays(year, month);
    }

}