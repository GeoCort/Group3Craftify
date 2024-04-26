package com.example.group3craftify;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {
    @Test
    public void testGetUsername() {
        User user = new User("2021","2122@mail.com","2i222");
        user.setUsername("JohnDoe");
        assertEquals("JohnDoe", user.getUsername());

    }
    @Test
    public void testSetUsername() {
        User user = new User();
        user.setUsername("JohnDoe");
        assertEquals("JohnDoe", user.getUsername());
    }

    public void testGetEmail() {
        User user = new User("2021","2122@mail.com","2i222");
        assertEquals("2122@mail.com", user.getEmail());
    }

    public void testSetEmail() {
        User user = new User();
        user.setEmail("mynewemail@gmail.com");
        assertEquals("mynewemail@gmail.com",user.getEmail());
    }

    public void testGetUserID() {
        User user = new User();
        user.setUserID("49951iop00023");
        assertEquals("49951iop00023", user.getUserID());
    }

    public void testSetUserID() {
        User user = new User("2021","2122@mail.com","2i222");
        user.setUserID("49951iop00023");
        assertEquals("49951iop00023", user.getUserID());
    }
}