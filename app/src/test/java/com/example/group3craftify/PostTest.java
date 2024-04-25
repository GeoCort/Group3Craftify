package com.example.group3craftify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostTest {

    @Test
    public void testConstructorWithId() {
        String id = "123";
        String title = "Title";
        String description = "Description";
        String craftName = "Craft";
        String category = "Category";
        String createdBy = "User";

        Post post = new Post(id, title, description, craftName, category, createdBy);

        assertEquals(id, post.getId());
        assertEquals(title, post.getTitle());
        assertEquals(description, post.getDescription());
        assertEquals(craftName, post.getCraftName());
        assertEquals(category, post.getCategory());
        assertEquals(createdBy, post.getCreatedBy());
    }


    @Test
    public void testConstructorWithoutId() {
        String title = "Title";
        String description = "Description";
        String craftName = "Craft";
        String category = "Category";
        String createdBy = "User";

        Post post = new Post(title, description, craftName, category, createdBy);

        assertNotNull(post.getId()); // ID should be generated
        assertEquals(title, post.getTitle());
        assertEquals(description, post.getDescription());
        assertEquals(craftName, post.getCraftName());
        assertEquals(category, post.getCategory());
        assertEquals(createdBy, post.getCreatedBy());
    }

    @Test
    public void testSetterGetter() {
        Post post = new Post();

        String id = "123";
        post.setId(id);
        assertEquals(id, post.getId());

        String category = "Category";
        post.setCategory(category);
        assertEquals(category, post.getCategory());

        String createdBy = "User";
        post.setCreatedBy(createdBy);
        assertEquals(createdBy, post.getCreatedBy());
    }
}