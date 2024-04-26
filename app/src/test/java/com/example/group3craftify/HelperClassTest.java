package com.example.group3craftify;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HelperClassTest {

    private HelperClass helper;

    @Before
    public void setUp() {
        helper = new HelperClass();
    }

    @Test
    public void testGetName() {
        helper.setName("John");
        assertEquals("John", helper.getName());
    }

    @Test
    public void testSetName() {
        helper.setName("Alice");
        assertEquals("Alice", helper.getName());
    }

    @Test
    public void testGetEmail() {
        helper.setEmail("john@example.com");
        assertEquals("john@example.com", helper.getEmail());
    }

    @Test
    public void testSetEmail() {
        helper.setEmail("alice@example.com");
        assertEquals("alice@example.com", helper.getEmail());
    }

    @Test
    public void testGetUsername() {
        helper.setUsername("john_doe");
        assertEquals("john_doe", helper.getUsername());
    }

    @Test
    public void testSetUsername() {
        helper.setUsername("alice_smith");
        assertEquals("alice_smith", helper.getUsername());
    }

    @Test
    public void testGetPassword() {
        helper.setPassword("password123");
        assertEquals("password123", helper.getPassword());
    }

    @Test
    public void testSetPassword() {
        helper.setPassword("securepassword456");
        assertEquals("securepassword456", helper.getPassword());
    }
}
