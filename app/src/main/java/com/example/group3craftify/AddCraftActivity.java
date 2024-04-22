package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddCraftActivity extends AppCompatActivity {
EditText titleInput;
EditText descInput;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_craft);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addCraftmain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String name =  intent.getStringExtra("keyCategory");
        titleInput = findViewById(R.id.addCraftTitle);
        descInput = findViewById(R.id.addCraftDesc);
        btn = findViewById(R.id.addCraftBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    // do something
                    finish();
                }
            }
        });
    }
    public boolean validateData(){
        if(titleInput.getText().toString().length() < 2){
            titleInput.setError("Title is too short");
            return false;
        }
        if(descInput.getText().toString().length() < 10 ){
            descInput.setError("Make description longer!");
            return false;
        }
        return true;
    }
}