package com.example.plantaid_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_Plant_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_details);

        String com_name = getIntent().getStringExtra("COMMON_NAME");
        String sci_name = getIntent().getStringExtra("SCIENTIFIC_NAME");
        String description = getIntent().getStringExtra("PLANT_DESC");
        int image = getIntent().getIntExtra("IMAGE",0);

        TextView commonNameTextView = findViewById(R.id.com_plant);
        TextView sciNameTextView = findViewById(R.id.sci_plant);
        TextView desciptionTextView = findViewById(R.id.plant_desc);
        ImageView imageView = findViewById(R.id.plant_image);

        commonNameTextView.setText(com_name);
        sciNameTextView.setText(sci_name);
        desciptionTextView.setText(description);
        imageView.setImageResource(image);

    }
}