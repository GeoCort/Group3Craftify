package com.example.group3craftify;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Craft {
    String creator;
    String title;
    String subTitle;
    String category;
    int followers;
    ArrayList<Post> posts;
    Craft(String title, String subTitle, String category){
        this.creator = "Admin";
        posts = new ArrayList<>();
        this.title = title;
        this.subTitle = subTitle;
        this.category = category;
        this.followers = 0;

    }

    public Craft(String creator, String title, String subTitle, String category) {
        this.creator = creator;
        this.title = title;
        this.subTitle = subTitle;
        this.category = category;
        followers = 0;
    }
    public String getPath(){
        return getCategory() + "/" + getTitle() + "/";
    }
    public void makePost(String title, String desc){
        String path = getPath();
        LocalDateTime dateTime;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String dateCreated = timestamp.toString();


        // New Post(Title Description)
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
