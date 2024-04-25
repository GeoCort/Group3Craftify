package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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

        setAllCategoriesButton();
        setWelcomeText();

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");
        String userID = intent.getStringExtra("userID");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        welcomeText.setText( getTimeStatement(hour) + userName);

        allCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CategoriesActivity when the button is clicked
                Intent intent =new Intent(HomeActivity.this, CategoriesActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("userID",userID);
                startActivity(intent);
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

    public void setAllCategoriesButton()
    {
        RelativeLayout.LayoutParams btnRelativeLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnRelativeLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnRelativeLayout.topMargin = 1400;
        btnRelativeLayout.height = 400;
        btnRelativeLayout.width = 1000;
        allCategoriesButton.setLayoutParams(btnRelativeLayout);
    }

    public void setWelcomeText()
    {
        RelativeLayout.LayoutParams textRelativeLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textRelativeLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textRelativeLayout.topMargin = 900;
        textRelativeLayout.width = 900;
        textRelativeLayout.height = 300;
        welcomeText.setLayoutParams(textRelativeLayout);
    }
}
