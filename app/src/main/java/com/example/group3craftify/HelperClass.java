package com.example.group3craftify;

// This class serves as a helper class for managing user data.
public class HelperClass {

    // Member variables to store user information
    String name, email, username, password;

    // Getter method for retrieving the user's name
    public String getName() {
        return name;
    }

    // Setter method for setting the user's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for retrieving the user's email
    public String getEmail() {
        return email;
    }

    // Setter method for setting the user's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for retrieving the user's username
    public String getUsername() {
        return username;
    }

    // Setter method for setting the user's username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the user's password
    public String getPassword() {
        return password;
    }

    // Setter method for setting the user's password
    public void setPassword(String password) {
        this.password = password;
    }

    // Constructor with parameters to initialize the user object with provided data
    public HelperClass(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Default constructor
    public HelperClass() {
    }

}
