package com.example.group3craftify;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

// get craft list from data base using setter method initializes a craft using name method
public class Category {
    String name;
    ArrayList<Craft> crafts;

    /**
     * instantiates a craft
     * @param name
     *
     */
    public Category(String name) {
        this.name = name;
        crafts= new ArrayList<>();
    }
    public void addCraft(Craft craft){
        crafts.add(craft);
    }
    public void deleteCraft(Craft craft){
        crafts.remove(craft);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Craft> getCrafts() {
        return crafts;
    }

    public void setCrafts(ArrayList<Craft> crafts) {
        this.crafts = crafts;
    }
}
