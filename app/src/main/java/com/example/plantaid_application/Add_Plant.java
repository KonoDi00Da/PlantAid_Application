package com.example.plantaid_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Add_Plant extends AppCompatActivity {

    ArrayList<PlantModel> plantArrayList = new ArrayList<>();
    int[] plantImages = {R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setPlantArrayList();

        Plant_List_Adapter adapter = new Plant_List_Adapter(this,plantArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setPlantArrayList(){
        String[] commonPlantName = getResources().getStringArray(R.array.common_plant_names);
        String[] sciPlantName = getResources().getStringArray(R.array.scientific_plant_names);

        for(int i = 0; i < commonPlantName.length; i++){
            plantArrayList.add(new PlantModel(commonPlantName[i],
                    sciPlantName[i],
                    plantImages[i]));
        }

    }
}