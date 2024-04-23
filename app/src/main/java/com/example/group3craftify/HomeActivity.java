package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeText;
    Button allCategoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        allCategoriesButton = findViewById(R.id.home_AllCategories);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        welcomeText.setText( getTimeStatement(hour) + userName + "! Your email is " + userEmail);

        allCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CategoriesActivity when the button is clicked
                startActivity(new Intent(HomeActivity.this, CategoriesActivity.class));
            }
        });
    }

    public String getTimeStatement(int hour)
    {
        if (hour >= 0 && hour < 12 )
        {
            return "Good morning, ";
        }

        else if (hour >= 12 && hour < 19)
        {
            return "Good afternoon, ";
        }

        return "Good evening, ";
    }
}
