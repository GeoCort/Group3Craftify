package com.example.group3craftify;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LoginTest
{
    @Mock
    FirebaseAuth dbAuth;

    @Mock
    Task task;

    @Test
    public void testLogin()
    {
        String email = "test@gmail.com";
        String password = "password123";

        dbAuth = Mockito.mock(FirebaseAuth.class);

        // Mock the behavior of FirebaseAuth.signInWithEmailAndPassword()
        Mockito.when(dbAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(task);

        dbAuth.signInWithEmailAndPassword(email, password);

        // Verify that signInWithEmailAndPassword was called with the correct parameters
        verify(dbAuth).signInWithEmailAndPassword(email, password);
    }
}
