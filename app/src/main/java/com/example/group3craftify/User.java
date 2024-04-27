package com.example.group3craftify;

/**
 *
 */
public class User {

    // Declare instance variables for username, email, and userID
    public String username;
    public String email;
    String userID; // This variable is package-private, accessible only within the same package

    /**
     * Empty constructor for firebase
     */
    public User() {}

    /**
     * Creates a user object before being added to a DB
     * @param username the username given by the user
     * @param email the email given by the user
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Generates a user that has already had an ID created
     * @param username username
     * @param email email
     * @param userID userID ideally from DB or generated previously
     */
    public User(String username, String email, String userID) {
        this.username = username;
        this.email = email;
        this.userID = userID;
    }

    // Getter method for username

    /**
     * Gets the username of the user
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username to username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns the email of the current user
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the username's email to email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the current user's ID
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * sets the userID to userID
     * @param userID the new userID for user
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
