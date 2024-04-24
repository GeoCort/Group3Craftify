package com.example.group3craftify;

import android.media.Image;

import java.util.UUID;

public class Post {

    String id;

    String title;

    String description;
    String craftName;
    Image image;
    String category;
    String createdBy;
    public Post() {
    }

    public Post(String id, String title, String description, String craftName, String category, String createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.createdBy = createdBy;
        this.category = category;
    }

    public Post(String title, String description, String craftName, String category, String createdBy) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.craftName = craftName;
        this.category = category;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(){
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(){
        this.description = description;
    }
    public Image getImage() {
        return image;
    }
    public void setImage() {
        this.image = image;
    }
    public String getCraftName(){
        return craftName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
