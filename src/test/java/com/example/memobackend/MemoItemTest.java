package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoItemTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSelectAlertTimeNormal() throws Exception {
        String title = "aaa";
        String time = "2024/04/29 15:00";
        String alertTimeSelection1 = "Time of Memo";
        String alertTimeSelection2 = "5 minutes ago";
        String alertTimeSelection3 = "10 minutes ago";
        String alertTimeSelection4 = "15 minutes ago";
        String alertTimeSelection5 = "30 minutes ago";
        String alertTimeSelection6 = "1 hour ago";
        String alertTimeSelection7 = "2 hours ago";
        MemoItem memoItem1 = new MemoItem(title, time, alertTimeSelection1, "");
        MemoItem memoItem2 = new MemoItem(title, time, alertTimeSelection2, "");
        MemoItem memoItem3 = new MemoItem(title, time, alertTimeSelection3, "");
        MemoItem memoItem4 = new MemoItem(title, time, alertTimeSelection4, "");
        MemoItem memoItem5 = new MemoItem(title, time, alertTimeSelection5, "");
        MemoItem memoItem6 = new MemoItem(title, time, alertTimeSelection6, "");
        MemoItem memoItem7 = new MemoItem(title, time, alertTimeSelection7, "");
        assertEquals("2024/04/29 15:00", memoItem1.getAlertTime());
        assertEquals("2024/04/29 14:55", memoItem2.getAlertTime());
        assertEquals("2024/04/29 14:50", memoItem3.getAlertTime());
        assertEquals("2024/04/29 14:45", memoItem4.getAlertTime());
        assertEquals("2024/04/29 14:30", memoItem5.getAlertTime());
        assertEquals("2024/04/29 14:00", memoItem6.getAlertTime());
        assertEquals("2024/04/29 13:00", memoItem7.getAlertTime());
    }

    @Test
    public void testSelectAlertTimeTimeFormatIsWrong() throws Exception {
        String title = "aaa";
        String time = "2024/4/29 15:0";
        String alertTimeSelection = "Time of Memo";
        MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, "");
        assertEquals("", memoItem.getAlertTime());
    }

    @Test
    public void testSelectAlertTimeAlertTimeSelectionIsEmpty() throws Exception {
        String title = "aaa";
        String time = "2024/04/29 15:00";
        String alertTimeSelection = "";
        String description = "123456";
        MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, description);
        assertEquals("aaa", memoItem.getTitle());
        assertEquals("2024/04/29 15:00", memoItem.getTime());
        assertEquals("", memoItem.getAlertTime());
        assertEquals("123456", memoItem.getDescription());
    }
}