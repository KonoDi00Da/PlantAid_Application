package com.example.plantaid_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantaid_application.Models.PlantModel;
import com.example.plantaid_application.Models.Plant_List_Adapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Add_Plant_Details extends AppCompatActivity {
    TextView commonNameTextView, sciNameTextView, desciptionTextView, txtWater, txtHarvest, txtCare, txtPestsDiseases;
    ImageView imgPlant;
    DatabaseReference database;
    Plant_List_Adapter myAdapter;
    ArrayList<PlantModel> plantsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_details);



//        String com_name = getIntent().getStringExtra("COMMON_NAME");
//        String sci_name = getIntent().getStringExtra("SCIENTIFIC_NAME");
//        String description = getIntent().getStringExtra("PLANT_DESC");
//        int image = getIntent().getIntExtra("IMAGE",0);

        commonNameTextView = findViewById(R.id.com_plant);
        sciNameTextView = findViewById(R.id.sci_plant);
        desciptionTextView = findViewById(R.id.plant_desc);
        txtWater = findViewById(R.id.txtWater);
        txtHarvest = findViewById(R.id.txtHarvest);
        txtCare = findViewById(R.id.txtCare);
        txtPestsDiseases = findViewById(R.id.txtPestsDisease);
        imgPlant = findViewById(R.id.plant_image);

//        commonNameTextView.setText(com_name);
//        sciNameTextView.setText(sci_name);
//        desciptionTextView.setText(description);
//        imgPlant.setImageResource(image);

    }
}