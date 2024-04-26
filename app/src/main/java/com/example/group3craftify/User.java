package com.example.group3craftify;

// Define a class named User
public class User {

    // Declare instance variables for username, email, and userID
    public String username;
    public String email;
    String userID; // This variable is package-private, accessible only within the same package

    // Default constructor
    public User() {}

    // Constructor with parameters for username and email
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Constructor with parameters for username, email, and userID
    public User(String username, String email, String userID) {
        this.username = username;
        this.email = email;
        this.userID = userID;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Setter method for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Setter method for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for userID
    public String getUserID() {
        return userID;
    }

    // Setter method for userID
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
