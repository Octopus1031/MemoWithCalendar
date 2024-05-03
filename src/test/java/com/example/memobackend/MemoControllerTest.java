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
        Set<MemoItem> memoItemSet = new HashSet<>();
        memoItemSet.add(new MemoItem("aaa", "2024/04/29 15:00",
                "Time of Memo", ""));
        memoItemSet.add(new MemoItem("bbb", "2024/04/30 12:00",
                "1 hour ago", "123456"));

        MemoController memoController = new MemoController();
        memoController.addMemo("aaa", "2024", "04", "29", "15", "00",
                "Time of Memo", "");
        memoController.addMemo("bbb", "2024", "04", "30", "12", "00",
                "1 hour ago", "123456");
        Set<MemoItem> result = memoController.getMemoItemSetOfMemoList();

        assertEquals(result, memoItemSet);
    }
}