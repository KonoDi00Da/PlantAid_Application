package com.example.plantaid_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Module_Identify_Plant_Organ extends AppCompatActivity {
    private ImageView imgPlant;
    private Button btnLeaf, btnFlower, btnFruit, btnBark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_identify_plant_organ);

        Intent intent = new Intent(this, Module_Identify_Plant_Result.class);

        imgPlant = findViewById(R.id.imgUserInput);
        btnLeaf = findViewById(R.id.btnLeaf);
        btnFlower = findViewById(R.id.btnFlower);
        btnFruit = findViewById(R.id.btnFruit);
        btnBark = findViewById(R.id.btnBark);
        String url = getIntent().getStringExtra("url");
        String imagePic = getIntent().getStringExtra("userPic");
        intent.putExtra("serviceUrl", url);
        intent.putExtra("imgPic", imagePic);


        // getting attached intent data
        Picasso.get().load(imagePic)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgPlant);

        btnLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organ = "leaf";
                intent.putExtra("plantOrgan", organ);
                startActivity(intent);
            }
        });

        btnFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organ = "flower";
                intent.putExtra("plantOrgan", organ);
                startActivity(intent);
            }
        });

        btnFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organ = "fruit";
                intent.putExtra("plantOrgan", organ);
                startActivity(intent);
            }
        });

        btnBark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organ = "bark";
                intent.putExtra("plantOrgan", organ);
                startActivity(intent);
            }
        });

    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}