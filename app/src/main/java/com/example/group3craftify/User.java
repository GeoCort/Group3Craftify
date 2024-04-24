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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
