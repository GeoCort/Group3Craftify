package com.example.group3craftify;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

// get craft list from data base using setter method initializes a craft using name method
public class Category {
    String name;
    ArrayList<Craft> crafts;
    /**
     * Creates an empty category with the name, name.
     * @param name name of the category
     */
    public Category(String name) {
        this.name = name;
        crafts= new ArrayList<>();
    }

    /**
     * Empty constructor for Firebase Instantiation
     */
    public Category(){

    }

    /**
     * Adds a craft to a linked list
     * @param craft Craft class object
     */
    public void addCraft(Craft craft){
        crafts.add(craft);
    }

    /**
     * removes a craft from an arraylist
     * @param craft Craft class object
     */
    public void deleteCraft(Craft craft){
        crafts.remove(craft);
    }

    /**
     * gets the category name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the category name to name
     * @param name String name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the Arraylist
     * @return ArrayList of type Craft
     */
    public ArrayList<Craft> getCrafts() {
        return crafts;
    }

    /**
     * Sets the arraylist of type Craft to crafts
     * @param crafts ArrayList of type Craft
     */
    public void setCrafts(ArrayList<Craft> crafts) {
        this.crafts = crafts;
    }
}
