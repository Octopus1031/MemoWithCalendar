package com.example.memobackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class MemoItem {
    String title;

    String time;

    String alert_time;

    String description;

    public MemoItem(String title, String time, String alert_time, String description) {
        this.title = title;
        this.time = time;
        this.alert_time = alert_time;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTime() {
        return this.time;
    }

    public String getAlert_time() {
        return this.alert_time;
    }

    public String getDescription() {
        return this.description;
    }
}
