package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabelTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetLabelName() throws Exception {
        String LabelName = "aaa";
        Label label = new Label(LabelName);
        assertEquals(LabelName, label.getName());
    }

    @Test
    public void testRepeat() throws Exception {
        String LabelName = "aaa";
        String anotherLabelName = "bbb";
        String LabelNameRepeat = "aaa";
        Label label = new Label(LabelName);
        Label anotherLabel = new Label(anotherLabelName);
        Label labelRepeat = new Label(LabelNameRepeat);
        assertFalse(label.equals(anotherLabel));
        assertTrue(label.equals(labelRepeat));
    }

    @Test
    public void testHashCode() throws Exception {
        String LabelName = "aaa";
        Label label = new Label(LabelName);
        assertEquals(96321, label.hashCode());
    }
}
