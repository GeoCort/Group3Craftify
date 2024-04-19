package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signUpRedirectText;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth dbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signUpRedirectText = findViewById(R.id.signUpRiderectText);
        loginButton = findViewById(R.id.login_button);

        dbAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> {
            loginUser();
        });

        signUpRedirectText.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }

    private void loginUser()
    {
        String email = loginEmail.getText().toString();       // Ask to update to email instead of username
        String password = loginPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            loginEmail.setError("Email can't be empty!");
            loginEmail.requestFocus();
        }

        else if (TextUtils.isEmpty(password))
        {
            loginPassword.setError("Password can't be empty!");
            loginPassword.requestFocus();
        }

        else
        {
            dbAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Login Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}