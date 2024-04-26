package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * SignUp Class registers a user and saves their information to Firebase
 * @author Victor Ly, George Ermakov
 * @since (4/14) Commit 6ce1d92955442af25ff1ce091e6090f75fb7735f
 */
public class SignupActivity extends AppCompatActivity {

    EditText signupName;
    EditText signupEmail;
    EditText signupUsername;
    EditText signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseAuth dbAuth;
    DatabaseReference mDatabase;

    /**
     * "Main" method that calls the helper functions and staging area for initializing variables
     * @author Victor Ly, George Ermakov
     * @since (4/14) Commit 6ce1d92955442af25ff1ce091e6090f75fb7735f
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dbAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(view -> createUser());
        loginRedirectText.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }

    /**
     * Function creates a user through name, email, user, and password saving it to Firebase
     * @author Victor Ly, George Ermakov
     * @since (4/15) Commit 55d7068987120fe66eccdcac7ab27566d33b8fcc
     */
    public void createUser()
    {
        String name = signupName.getText().toString();
        String email = signupEmail.getText().toString();
        String username = signupUsername.getText().toString();
        String password = signupPassword.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "All fields must be filled!", Toast.LENGTH_LONG).show();
            return;
        }

        dbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = dbAuth.getCurrentUser();
                    String userID = user.getUid();
                    writeNewUser(userID, name, email);

                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    intent.putExtra("userName", name);
                    intent.putExtra("userEmail", email);
                    intent.putExtra("userID",userID); // current user id

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Registration error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Writes the signed up user information to Firebase under Realtime Database
     * @author Victor Ly
     * @param userId String
     * @param name String
     * @param email String
     * @since (4/15) Commit 55d7068987120fe66eccdcac7ab27566d33b8fcc
     */
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email,userId);
        mDatabase.child("Users").child(userId).setValue(user);
    }
}
