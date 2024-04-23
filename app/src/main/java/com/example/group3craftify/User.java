package com.example.group3craftify;

public class User
{
    public String username;
    public String email;
    String userID;

    public User() {}

    public User(String username, String email)
    {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String userID) {
        this.username = username;
        this.email = email;
        this.userID = userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
