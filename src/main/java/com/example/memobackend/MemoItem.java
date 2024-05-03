package com.example.memobackend;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemoItem {
    String title;

    String time; // time和alert_time的格式為yyyy/MM/dd hh:mm

    String alert_time;

    String description;

    public MemoItem(String title, String time, String alertTimeSelection, String description) {
        this.title = title;
        this.time = time;
        this.alert_time = SelectAlertTime(time, alertTimeSelection);
        this.description = description;
    }

    public String SelectAlertTime(String time, String alertTimeSelection) {
        if (!Objects.equals(alertTimeSelection, "")) {
            // 將time的內容分離出年、月、日、時、分5個元素
            String regex = "(\\d{4})/(\\d{2})/(\\d{2}) (\\d{2}):(\\d{2})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(time);
            int year = 0, month = 0, day = 0, hour = 0, minute = 0;
            if (matcher.matches()) { // 如果沒有matcher.matches()，將無法取得對應元素
                year = Integer.parseInt(matcher.group(1));
                month = Integer.parseInt(matcher.group(2));
                day = Integer.parseInt(matcher.group(3));
                hour = Integer.parseInt(matcher.group(4));
                minute = Integer.parseInt(matcher.group(5));
            } else {
                System.out.println("未找到匹配的日期時間格式！");
                return "";
            }
            // 將分離出的5個元素放進LocalDateTime裡，方便做時間的加減
            LocalDateTime standardTime = LocalDateTime.of(year, month, day, hour, minute);
            // 透過選擇出的alertTimeSelection，對time做加減成alert_time，接著將alert_time轉String後回傳
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            return timeIsAddedOrSubtractedToAlertTime(standardTime, alertTimeSelection).format(formatter);
        } else {
            return "";
        }
    }

    public LocalDateTime timeIsAddedOrSubtractedToAlertTime(LocalDateTime standardTime, String alertTimeSelection) {
        return switch (alertTimeSelection) {
            case "5 minutes ago" -> standardTime.minusMinutes(5);
            case "10 minutes ago" -> standardTime.minusMinutes(10);
            case "15 minutes ago" -> standardTime.minusMinutes(15);
            case "30 minutes ago" -> standardTime.minusMinutes(30);
            case "1 hour ago" -> standardTime.minusHours(1);
            case "2 hours ago" -> standardTime.minusHours(2);
//            case "1 day ago" -> standardTime.minusDays(1);
//            case "1 days ago" -> standardTime.minusDays(2);
//            case "1 week ago" -> standardTime.minusDays(7);
            default -> standardTime;
        };
    }

    public String getTitle() {
        return this.title;
    }

    public String getTime() {
        return this.time;
    }

    public String getAlertTime() {
        return this.alert_time;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemoItem other = (MemoItem) obj;
        return title.equals(other.getTitle()) && time.equals(other.getTime())
                && alert_time.equals(other.getAlertTime()) && description.equals(other.getDescription());
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, time, alert_time, description);
    }
}
