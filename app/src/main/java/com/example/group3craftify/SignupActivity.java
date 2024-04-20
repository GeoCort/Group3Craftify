package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

        EditText signupName, signupEmail, signupUsername, signupPassword;
        TextView loginRiderectText;
        Button signupButton;
        FirebaseDatabase database;
        FirebaseAuth dbAuth;
        DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail= findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRiderectText = findViewById(R.id.loginRiderectText);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dbAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(view -> {
            createUser();
        });

        loginRiderectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void writeNewUser(String userId, String name, String email)
    {
        User user = new User(name, email);

        mDatabase.child("Users").child(userId).setValue(user);
    }

    private void createUser()
    {
        String name = signupName.getText().toString();
        String email = signupEmail.getText().toString();
        String username = signupUsername.getText().toString();
        String password = signupPassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            signupName.setError("Name can't be empty!");
            signupName.requestFocus();
        }

        else if (TextUtils.isEmpty(email))
        {
            signupName.setError("Email can't be empty!");
            signupName.requestFocus();
        }

        else if (TextUtils.isEmpty(username))
        {
            signupName.setError("Username can't be empty!");
            signupName.requestFocus();
        }

        else if (TextUtils.isEmpty(password))
        {
            signupName.setError("Password can't be empty!");
            signupName.requestFocus();
        }

        else
        {
            dbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        FirebaseUser user = dbAuth.getCurrentUser();
                        String userId = user.getUid();
                        writeNewUser(userId, name, email);
                        Toast.makeText(SignupActivity.this, "Successful Register!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this, "Registration error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}