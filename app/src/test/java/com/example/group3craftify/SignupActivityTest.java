package com.example.group3craftify;

import static org.junit.jupiter.api.Assertions.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import static org.mockito.Mockito.*;

import android.widget.EditText;

import org.junit.jupiter.api.Test;

class SignupActivityTest
{
    private EditText signupName, signupEmail, signupUsername, signupPassword;
    SignupActivity activity = new SignupActivity();


    @Test
    void writeNewUser()
    {
        FirebaseAuth auth = mock(FirebaseAuth.class);
        FirebaseUser user = mock(FirebaseUser.class);


        activity.signupName = signupName;
        activity.signupEmail = signupEmail;
        activity.signupUsername = signupUsername;
        activity.signupPassword = signupPassword;

        activity.createUser();


    }
}