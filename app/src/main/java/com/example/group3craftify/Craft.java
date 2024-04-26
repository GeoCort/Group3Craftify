package com.example.group3craftify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// Class representing a craft
public class Craft {
    String createdBy; // Creator of the craft
    String craftTitle; // Title of the craft
    String craftDesc; // Description of the craft
    String craftID; // Unique ID of the craft
    String category; // Category of the craft

    // Default constructor
    Craft(){}

    // Constructor with parameters
    Craft(String createdBy, String craftTitle, String craftDesc, String category){
        UUID id = UUID.randomUUID(); // Generate a random UUID
        this.craftID = id.toString(); // Convert UUID to string and assign it as craftID
        this.createdBy = createdBy; // Assign createdBy
        this.craftTitle = craftTitle; // Assign craftTitle
        this.craftDesc = craftDesc; // Assign craftDesc
        this.category = category; // Assign category
    }

    // Constructor with parameters including craftID
    Craft(String craftID, String createdBy, String craftTitle, String craftDesc, String category){
        this.craftID = craftID; // Assign craftID
        this.createdBy = createdBy; // Assign createdBy
        this.craftTitle = craftTitle; // Assign craftTitle
        this.craftDesc = craftDesc; // Assign craftDesc
        this.category = category; // Assign category
    }

    // Getters and setters
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCraftTitle() {
        return craftTitle;
    }

    public void setCraftTitle(String craftTitle) {
        this.craftTitle = craftTitle;
    }

    public String getCraftDesc() {
        return craftDesc;
    }

    public void setCraftDesc(String craftDesc) {
        this.craftDesc = craftDesc;
    }

    public String getCraftID() {
        return craftID;
    }

    public void setCraftID(String craftID) {
        this.craftID = craftID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
