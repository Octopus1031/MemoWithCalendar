package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MemoControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMemoItemSetAndAddMemo() throws Exception {
        Set<MemoItem> expectedMemoItemSet = new HashSet<>();
        expectedMemoItemSet.add(new MemoItem("aaa", "2024/04/29 15:00",
                "Time of Memo", "", 0L));
        expectedMemoItemSet.add(new MemoItem("bbb", "2024/04/30 12:00",
                "1 hour ago", "123456", 0L));

        MemoController memoController = new MemoController();
        memoController.addMemo("aaa", "2024", "04", "29", "15", "00",
                "Time of Memo", "");
        memoController.addMemo("bbb", "2024", "04", "30", "12", "00",
                "1 hour ago", "123456");
        Set<MemoItem> resultMemoItemSet = memoController.getMemoItemSetOfMemoList();

        for (MemoItem expectedMemoItem : expectedMemoItemSet) {
            boolean isMatched = false;
            for (MemoItem resultMemoItem : resultMemoItemSet) {
                if (expectedMemoItem.getTitle().equals(resultMemoItem.getTitle())
                        && expectedMemoItem.getTime().equals(resultMemoItem.getTime())
                        && expectedMemoItem.getAlertTimeSelection().equals(resultMemoItem.getAlertTimeSelection())
                        && expectedMemoItem.getAlertTime().equals(resultMemoItem.getAlertTime())
                        && expectedMemoItem.getDescription().equals(resultMemoItem.getDescription())) {
                    isMatched = true;
                    break;
                }
            }
            assertTrue(isMatched);
        }
    }
}