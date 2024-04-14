package com.example.group3craftify;

// get craft list from data base using setter method initializes a craft using name method
public class Category {
    String name;
    Craft crafts[];

    /**
     * instantiates a craft
     * @param name
     *
     */
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Craft[] getCrafts() {
        return crafts;
    }

    public void setCrafts(Craft[] crafts) {
        this.crafts = crafts;
    }
}
