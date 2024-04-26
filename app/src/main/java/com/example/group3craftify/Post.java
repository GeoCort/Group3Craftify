package com.example.group3craftify;

import java.util.UUID; // Import statement for UUID class

public class Post {

    String id; // Unique identifier for the post
    String title; // Title of the post
    String description; // Description of the post
    String craftName; // Name of the craft associated with the post
    String category; // Category of the post
    String createdBy; // Username of the user who created the post

    // Default constructor
    public Post() {
    }

    // Constructor with all parameters
    public Post(String id, String title, String description, String craftName, String category, String createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.createdBy = createdBy;
        this.category = category;
    }

    // Constructor without id (generates a random UUID as id)
    public Post(String title, String description, String craftName, String category, String createdBy) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.category = category;
        this.createdBy = createdBy;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for craftName
    public String getCraftName(){
        return craftName;
    }

    // Getter for createdBy
    public String getCreatedBy() {
        return createdBy;
    }

    // Setter for createdBy
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }
}
