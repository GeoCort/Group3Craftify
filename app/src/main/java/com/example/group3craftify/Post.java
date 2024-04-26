package com.example.group3craftify;

import java.util.UUID; // Import statement for UUID class

/**
 * Post class represents a post in the app.
 * Users can create posts with titles, descriptions, associated crafts, categories, and usernames.
 * Each post has a unique identifier generated using UUID.
 *
 * @author George Cortes, Juan Sanchez
 * @since (4/19) Commit d63b1ce9ea762e5808f591e30a7df821e2f723c8
 */
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

    /**
     * Constructs a Post object with the specified id, title, description, craftName, category, and createdBy.
     *
     * @param id The unique identifier for the post.
     * @param title The title of the post.
     * @param description The description of the post.
     * @param craftName The name of the craft associated with the post.
     * @param category The category of the post.
     * @param createdBy The username of the user who created the post.
     */
    public Post(String id, String title, String description, String craftName, String category, String createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.createdBy = createdBy;
        this.category = category;
    }

    // Constructor without id (generates a random UUID as id)

    /**
     * Constructs a Post object with a randomly generated unique identifier (UUID),
     * and the specified title, description, craftName, category, and createdBy.
     *
     * @param title The title of the post.
     * @param description The description of the post.
     * @param craftName The name of the craft associated with the post.
     * @param category The category of the post.
     * @param createdBy The username of the user who created the post.
     */
    public Post(String title, String description, String craftName, String category, String createdBy) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.category = category;
        this.createdBy = createdBy;
    }

    // Getter for id

    /**
     * Returns the unique identifier of the post.
     *
     * @return The unique identifier of the post.
     */
    public String getId() {
        return id;
    }

    // Setter for id

    /**
     * Sets the unique identifier of the post.
     *
     * @param id The unique identifier to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    // Getter for title

    /**
     * Returns the title of the post.
     *
     * @return The title of the post.
     */
    public String getTitle() {
        return title;
    }

    // Getter for description

    /**
     * Returns the description of the post.
     *
     * @return The description of the post.
     */
    public String getDescription() {
        return description;
    }

    // Getter for craftName

    /**
     * Returns the name of the craft associated with the post.
     *
     * @return The name of the craft associated with the post.
     */
    public String getCraftName(){
        return craftName;
    }

    // Getter for createdBy

    /**
     * Returns the username of the user who created the post.
     *
     * @return The username of the user who created the post.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    // Setter for createdBy

    /**
     * Sets the username of the user who created the post.
     *
     * @param createdBy The username to set.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Getter for category

    /**
     * Returns the category of the post.
     *
     * @return The category of the post.
     */
    public String getCategory() {
        return category;
    }

    // Setter for category

    /**
     * Sets the category of the post.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }
}