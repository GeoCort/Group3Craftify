package com.example.group3craftify;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {
    @Test
    public void testGetUsername() {
        User user = new User();
        user.setUserID("JohnDoe");
        assertEquals("JohnDoe", user.getUsername());

    }
    @Test
    public void testSetUsername() {
        User user = new User();
        user.setUserID("JohnDoe");
        assertEquals("JohnDoe", user.getUsername());
    }

    public void testGetEmail() {
    }

    public void testSetEmail() {
    }

    public void testGetUserID() {
    }

    public void testSetUserID() {
    }
}