package com.example.group3craftify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Activity for adding a craft
/**
 * Activity for creating and adding a craft
 * @author George Cortes
 * @since 4/22
 */
public class AddCraftActivity extends AppCompatActivity {
    EditText titleInput; // Input field for craft title
    EditText descInput; // Input field for craft description
    Button btn; // Button for adding craft
    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_add_craft);

        // Apply insets listener for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addCraftmain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve intent data
        Intent intent = getIntent();
        String name =  intent.getStringExtra("keyCategory");
        String userName = intent.getStringExtra("userName");

        // Initialize input fields and button
        titleInput = findViewById(R.id.addCraftTitle);
        descInput = findViewById(R.id.addCraftDesc);
        btn = findViewById(R.id.addCraftBtn);

        // Set click listener for the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input data
                if(validateData()){
                    // Get craft title, description, and user ID
                    String title = titleInput.getText().toString();
                    String desc = descInput.getText().toString();
                    String id = intent.getStringExtra("userID");

                    // Create Craft object
                    Craft craft = new Craft(userName,title,desc,name);

                    // Store craft data in the database
                    setData(name,craft);
                }
            }
        });
    }

    // Method to set craft data in the database

    /**
     * Saves a craft into the database
     * @param name The category name
     * @param obj Craft object created in OnCreate
     */
    public void setData(String name, Craft obj){
        // Get user info somehow

        // Get database reference
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(name).child(obj.getCraftID());

        // Set craft data in the database
        ref.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Display success message
                Toast.makeText(AddCraftActivity.this, "This is being added into the db", Toast.LENGTH_SHORT).show();

                // Finish the activity
                finish();
            }
        });
    }

    // Method to validate input data
    /**
     * Ensures that data is not too small
     * Title larger than 4 characters
     * Description larger than 10 characters
     * @return boolean if minimum specifications met
     */
    public boolean validateData(){
        // Check if title is too short
        if(titleInput.getText().toString().length() < 2){
            titleInput.setError("Title is too short");
            return false;
        }
        // Check if description is too short
        if(descInput.getText().toString().length() < 10 ){
            descInput.setError("Make description longer!");
            return false;
        }
        // Data is valid
        return true;
    }
}
