package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MemoListTest {
    String title = "aaa";
    String time = "2024/04/29 15:00";
    String alertTimeSelection = "Time of Memo";
    String description = "123456";
    Long id = 0L;
    MemoItem memoItem = new MemoItem(title, time, alertTimeSelection, description, id);
    MemoList memoList = new MemoList();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddMemoItem() throws Exception {
        memoList.addMemoItem(memoItem);
        assertTrue(memoList.containsMemoItem(memoItem));
    }

    @Test
    public void testRemoveMemoItem() throws Exception {
        memoList.addMemoItem(memoItem);
        memoList.removeMemoItemById(memoItem.getId());
        assertFalse(memoList.containsMemoItem(memoItem));
    }

    @Test
    public void testGetMemoItems() throws Exception {
        memoList.addMemoItem(memoItem);
        String title = "aaa";
        String time = "2024/04/29 15:00";
        String alertTimeSelection = "Time of Memo";
        String description = "123456";
        MemoItem memoItem2 = new MemoItem(title, time, alertTimeSelection, description, 0L);
        memoList.addMemoItem(memoItem2);
        Set<MemoItem> memoItemSet = new HashSet<>();
        memoItemSet.add(memoItem);
        memoItemSet.add(memoItem2);
        assertEquals(memoItemSet, memoList.getMemoItems());
    }
}