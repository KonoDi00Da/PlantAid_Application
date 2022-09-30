package com.example.plantaid_application.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {
    DatabaseReference db;
    ArrayList<String> addPlantList = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db){
        this.db = db;
    }

    //read data
    public ArrayList<String> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return addPlantList;
    }

    private void fetchData(DataSnapshot dataSnapshot){
        addPlantList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            String comName = ds.getValue(PlantModel.class).getCommonPlantName();
            addPlantList.add(comName);
            String sciName = ds.getValue(PlantModel.class).getSciName();
            addPlantList.add(sciName);
        }
    }

}
