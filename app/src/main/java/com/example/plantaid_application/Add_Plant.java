package com.example.plantaid_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.plantaid_application.Models.PlantListModel;
import com.example.plantaid_application.Models.PlantModel;
import com.example.plantaid_application.Models.Plant_List_Adapter;
import com.example.plantaid_application.Models.RecyclerViewInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class Add_Plant extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<PlantListModel> plantArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        recyclerView = findViewById(R.id.mRecyclerView);
        plantArrayList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        Plant_List_Adapter recyclerAdapter = new Plant_List_Adapter(plantArrayList,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Plants");
        databaseReference.child("Plants").keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PlantListModel plantListModel = dataSnapshot.getValue(PlantListModel.class);
                    plantArrayList.add(plantListModel);
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/b.in/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }


    @Override
    public void onItemClick(int position) {
//        Intent intent = new Intent(Add_Plant.this,Add_Plant_Details.class);
//
//        //use parceable here
//        intent.putExtra("COMMON_NAME", plantArrayList.get(position).getCommonPlantName());
//        intent.putExtra("SCIENTIFIC_NAME", plantArrayList.get(position).getSciName());
//        intent.putExtra("PLANT_DESC", plantArrayList.get(position).getPlantDesc());
//        intent.putExtra("IMAGE", plantArrayList.get(position).getImage());

//        startActivity(intent);
    }
}