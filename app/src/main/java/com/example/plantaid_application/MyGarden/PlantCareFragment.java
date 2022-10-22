package com.example.plantaid_application.MyGarden;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.Adapter.ReminderPlant_Adapter;
import com.example.plantaid_application.Adapter.User_MyGarden_Adapter;
import com.example.plantaid_application.MainHome;
import com.example.plantaid_application.Models.PlantReminderModel;
import com.example.plantaid_application.Models.User_Plants;
import com.example.plantaid_application.Module_Identify_Plant_Result;
import com.example.plantaid_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantCareFragment extends Fragment {
    private CardView cardCustom, cardWater, cardFertilize, cardRepot;
    private String plantCommonName, userKey;
    private TextView txtTest;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private ReminderPlant_Adapter cAdapter;
    FirebaseDatabase database;

    private RecyclerView recyclerView;
    ArrayList<PlantReminderModel> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_care, container, false);
        Module_MyGarden_Details activity = (Module_MyGarden_Details) getActivity();
        plantCommonName = activity.getCommonName();
        userKey = activity.getUserKey();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardCustom = view.findViewById(R.id.cardCustom);
        cardRepot = view.findViewById(R.id.cardRepot);
        cardWater = view.findViewById(R.id.cardWater);
        cardFertilize = view.findViewById(R.id.cardFertilize);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        Intent intent = new Intent(getActivity(), PlantCare_Add_Reminder.class);
        intent.putExtra("plantCommonName", plantCommonName);
        intent.putExtra("userKey", userKey);

        cardWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "Water";
                intent.putExtra("taskReminder", task);
                startActivity(intent);
            }
        });

        cardRepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "Repot";
                intent.putExtra("taskReminder", task);
                startActivity(intent);
            }
        });

        cardFertilize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "Fertilize";
                intent.putExtra("taskReminder", task);
                startActivity(intent);
            }
        });

        cardCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "Custom";
                intent.putExtra("taskReminder", task);
                startActivity(intent);
            }
        });


        try{// Add the following lines to create RecyclerView

            recyclerView = view.findViewById(R.id.reminder1RecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setHasFixedSize(true);
            cAdapter = new ReminderPlant_Adapter(list, getActivity());
            recyclerView.setAdapter(cAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("myGarden").child(userKey).child("plantReminders");
//            databaseReference.child("Users").keepSynced(true);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            PlantReminderModel plantReminders = dataSnapshot.getValue(PlantReminderModel.class);
                            list.add(plantReminders);
                        }
                        cAdapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    toast("Error");

                }
            });

        } catch (NullPointerException e){
            Log.e("TEST", "Error: " + e.toString());
        }
    }

    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}