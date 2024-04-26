package com.example.group3craftify;

import junit.framework.TestCase;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class CategoryTest extends TestCase {

    public void testAddCraft() {
        Category c = new Category("sports");
        c.addCraft(new Craft("admin","football","username","sports"));
        assertEquals(1,c.getCrafts().size());

    }

    public void testDeleteCraft() {
        Category cat = new Category("sports");
        Craft c = new Craft("admin","football","username","sports");
        cat.addCraft(c);
        cat.crafts.remove(c);
        assertEquals(0,cat.crafts.size());
    }

    public void testTestGetName() {
        Category c = new Category("sports");
        c.addCraft(new Craft("admin","football","username","sports"));
        assertEquals("football",c.getCrafts().get(0).getCraftTitle());

    }

    public void testTestSetName() {
        Category c = new Category("sports");
        c.setName("Poetry");
        assertEquals("Poetry",c.getName());
    }

    public void testGetCrafts() {
        Category c = new Category("Poetry");
        assertEquals("Poetry",c.getName());
    }

    public void testSetCrafts() {
        Category c = new Category("Poetry");
        c.addCraft(new Craft("admin","Shakespeare","userName",c.getName()));
        c.addCraft(new Craft("admin","Julia Child","userName",c.getName()));
        ArrayList<Craft> crafts = new ArrayList<>();
        crafts.add(new Craft("admin","Julia Child","userName",c.getName()));
        c.setCrafts(crafts);
        assertEquals(1,c.crafts.size());
    }
}