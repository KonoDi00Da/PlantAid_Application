package com.example.plantaid_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantaid_application.Models.PlantListModel;
import com.example.plantaid_application.Models.PlantModel;
import com.example.plantaid_application.Models.Plant_List_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Add_Plant_Details extends AppCompatActivity {
    TextView commonNameTextView, sciNameTextView, descriptionTextView, txtWater, txtHarvest, txtCare, txtPestsDiseases,ytLink;
    ImageView imgPlant;
    String key, comPlant, sciPlant, age;
    EditText plantAge;
    Button addToGardenBtn;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    Plant_List_Adapter myAdapter;
    ArrayList<PlantListModel> plantListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_details);

//        String com_name = getIntent().getStringExtra("COMMON_NAME");
//        String sci_name = getIntent().getStringExtra("SCIENTIFIC_NAME");
//        String description = getIntent().getStringExtra("PLANT_DESC");
//        int image = getIntent().getIntExtra("IMAGE",0);
        //Initialize firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        commonNameTextView = findViewById(R.id.com_plant);
        sciNameTextView = findViewById(R.id.sci_plant);
        descriptionTextView = findViewById(R.id.plant_desc);
        txtWater = findViewById(R.id.txtWater);
        txtHarvest = findViewById(R.id.txtHarvest);
        txtCare = findViewById(R.id.txtCare);
        txtPestsDiseases = findViewById(R.id.txtPestsDisease);
        imgPlant = findViewById(R.id.plant_image);
        ytLink = findViewById(R.id.ytLink);
        plantAge = findViewById(R.id.plantAge);
        addToGardenBtn = findViewById(R.id.addToGardenBtn);

        Picasso.get().load(getIntent().getStringExtra("plant_image"))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgPlant);

        commonNameTextView.setText(getIntent().getStringExtra("com_plant"));
        sciNameTextView.setText(getIntent().getStringExtra("sci_plant"));
        descriptionTextView.setText(getIntent().getStringExtra("plant_desc"));
        txtWater.setText(getIntent().getStringExtra("txtWater"));
        txtHarvest.setText(getIntent().getStringExtra("txtHarvest"));
        txtCare.setText(getIntent().getStringExtra("txtCare"));
        txtPestsDiseases.setText(getIntent().getStringExtra("txtPestsDisease"));
        ytLink.setText(getIntent().getStringExtra("ytLink"));
        key = getIntent().getStringExtra("key");
        comPlant = getIntent().getStringExtra("com_plant");


        addToGardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference plantRef = database.getReference("Plants");
                DatabaseReference userRef = database.getReference("Users").child(currentUser.getUid());
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String plantID = "myGarden";
                        PlantListModel plantListModel = snapshot.getValue(PlantListModel.class);
                        userRef.child(plantID).child(key).setValue(comPlant);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


//        commonNameTextView.setText(com_name);
//        sciNameTextView.setText(sci_name);
//        desciptionTextView.setText(description);
//        imgPlant.setImageResource(image);

    }
}