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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Login Class signs a user in through Firebase authentication and saves their username throughout the app
 * @author Victor Ly, George Ermakov
 * @since (4/17) Commit aeb9a69cd4c157738217f33b2ca5313785a29499
 */
public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signUpRedirectText;
    FirebaseAuth dbAuth;

    /**
     * "Main" method that calls the helper functions and staging area for initializing variables
     * @author Victor Ly, George Ermakov
     * @since (4/17) Commit aeb9a69cd4c157738217f33b2ca5313785a29499
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signUpRedirectText = findViewById(R.id.signUpRedirectText);
        dbAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> loginUser());
        signUpRedirectText.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
    }

    /**
     * Authenticate a user through Firebase
     * @author Victor Ly, George Ermakov
     * @since (4/17) Commit aeb9a69cd4c157738217f33b2ca5313785a29499
     */
    private void loginUser() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Email and password cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        dbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(dbAuth.getCurrentUser().getUid());
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user = snapshot.getValue(User.class);
                            if (user != null) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("userName", user.username);
                                intent.putExtra("userEmail", user.email);
                                if(user.getUserID() == null) {
                                    intent.putExtra("userID", snapshot.getKey());
                                }else{
                                    intent.putExtra("userID", user.getUserID());

                                }
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, "Failed to read user data: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Login error! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
