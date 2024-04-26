package com.example.memobackend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser() {
        User user = new User("1", "John", "123@gmail.com");
        assertEquals("1", user.getId());
        assertEquals("John", user.getName());
        assertEquals("123@gmail.com", user.getEmail());
    }
}