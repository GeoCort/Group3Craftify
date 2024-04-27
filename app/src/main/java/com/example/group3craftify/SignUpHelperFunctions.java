package com.example.group3craftify;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpHelperFunctions
{
    FirebaseAuth dbAuth;
    DatabaseReference mDatabase;

    public void createUser(String email, String password)
    {
        dbAuth = FirebaseAuth.getInstance();
        dbAuth.createUserWithEmailAndPassword(email,password);
    }

    public void writeNewUser(String userId, String name, String email)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        User user = new User(name, email, userId);
        mDatabase.child("Users").child(userId).setValue(user);
    }

}
