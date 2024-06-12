package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

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
        MemoItem memoItem1 = new MemoItem(title, time, alertTimeSelection1, "", 0L);
        MemoItem memoItem2 = new MemoItem(title, time, alertTimeSelection2, "", 0L);
        MemoItem memoItem3 = new MemoItem(title, time, alertTimeSelection3, "", 0L);
        MemoItem memoItem4 = new MemoItem(title, time, alertTimeSelection4, "", 0L);
        MemoItem memoItem5 = new MemoItem(title, time, alertTimeSelection5, "", 0L);
        MemoItem memoItem6 = new MemoItem(title, time, alertTimeSelection6, "", 0L);
        MemoItem memoItem7 = new MemoItem(title, time, alertTimeSelection7, "", 0L);
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
        MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, "", 0L);
        assertEquals("", memoItem.getAlertTime());
    }

    @Test
    public void testSelectAlertTimeAlertTimeSelectionIsEmpty() throws Exception {
        String title = "aaa";
        String time = "2024/04/29 15:00";
        String alertTimeSelection = "";
        String description = "123456";
        MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, description, 0L);
        assertEquals("aaa", memoItem.getTitle());
        assertEquals("2024/04/29 15:00", memoItem.getTime());
        assertEquals("", memoItem.getAlertTimeSelection());
        assertEquals("", memoItem.getAlertTime());
        assertEquals("123456", memoItem.getDescription());
        assertEquals(Optional.of(0L), Optional.of(memoItem.getId()));
    }

    @Test
    public void parseDayFormatToInt() throws Exception {
        String title = "aaa";
        String time = "2024/06/05 15:00";
        String alertTimeSelection = "Time of Memo";
        String description = "123456";
        MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, description, 0L);
        assertEquals(20240605, memoItem.parseDayFormatToInt());

    }

    @Test
    public void checkMemoItemNotRepeat() throws Exception {
        String title1 = "aaa";
        String time1 = "2024/04/29 15:00";
        String alertTimeSelection1 = "";
        String description1 = "123456";
        MemoItem memoItem1 = new MemoItem(title1, time1, alertTimeSelection1, description1, 0L);
        assertTrue(memoItem1.equals(memoItem1));
        String title2 = "aaa";
        String time2 = "2024/04/29 15:00";
        String alertTimeSelection2 = "";
        String description2 = "123456";
        MemoItem memoItem2 = new MemoItem(title2, time2, alertTimeSelection2, description2, 0L);
        assertTrue(memoItem1.equals(memoItem2));
        MemoItem memoItem3 = null;
        assertFalse(memoItem1.equals(memoItem3));
    }
}