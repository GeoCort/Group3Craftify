package com.example.group3craftify;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.os.UserManager;

public class SignUpTest
{
    SignUpHelperFunctions signTest;
    @Mock
    FirebaseAuth dbAuth;
    @Mock
    DatabaseReference mDatabase;
    @Mock
    Task task;


    @Test
    public void testCreateUser()
    {
        signTest = new SignUpHelperFunctions();
        dbAuth = Mockito.mock(FirebaseAuth.class);

        String email = "test@gmail.com";
        String password = "password123";

        // Mock the behavior of FirebaseAuth.createUserWithEmailAndPassword()
        Mockito.when(dbAuth.createUserWithEmailAndPassword(anyString(), anyString())).thenReturn(task);

        // Call test method
        dbAuth.createUserWithEmailAndPassword(email, password);

        // Verify that createUserWithEmailAndPassword was called with the correct parameters
        verify(dbAuth).createUserWithEmailAndPassword(email, password);
    }

    @Test
    public void testWriteUser()
    {
        signTest = new SignUpHelperFunctions();
        dbAuth = Mockito.mock(FirebaseAuth.class);
        mDatabase = Mockito.mock(DatabaseReference.class);

        String userId = "test ID";
        String name = "John Doe";
        String email = "john@example.com";
        User user = new User(name, email,userId);

        mDatabase.child("Users").child(userId).setValue(user);

        // Verify that the child is called with the correct user object and path
        verify(mDatabase).child("Users").child(userId).setValue(any(User.class));
        verify(mDatabase).child("Users").child(userId).setValue(new User(name, email, userId));
    }
}