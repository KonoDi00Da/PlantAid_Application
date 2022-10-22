package com.example.plantaid_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.Adapter.ReminderPlantAll_Adapter;
import com.example.plantaid_application.Adapter.ReminderPlant_Adapter;
import com.example.plantaid_application.Models.PlantReminderModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Module_Today extends Fragment {

    private TextView userGreeting;
    private String fname, lname, email;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ReminderPlantAll_Adapter cAdapter;

    private RecyclerView recyclerView;
    ArrayList<PlantReminderModel> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module__today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        try{// Add the following lines to create RecyclerView

            userGreeting = view.findViewById(R.id.txtUserName);
            DatabaseReference reference = database.getReference("Users").child(currentUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    if (user != null){
                        fname = user.firstName;
                        lname = user.lastName;
                        email = user.email;
                        userGreeting.setText("Welcome, " + fname + "!");
                    }
                    else{
                        userGreeting.setText("Hi there!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            recyclerView = view.findViewById(R.id.reyclerViewAllReminders);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setHasFixedSize(true);
            cAdapter = new ReminderPlantAll_Adapter(list, getActivity());
            recyclerView.setAdapter(cAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("myGarden");
//            databaseReference.child("Users").keepSynced(true);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String key = dataSnapshot.getKey();
                            Log.d("Module_today", "onDataChange: 1    " + dataSnapshot.getKey());
                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                                Log.d("Module_today", "onDataChange: 2    " + dataSnapshot2.getKey());
                                for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()){
                                    Log.d("Module_today", "onDataChange: 3    " + dataSnapshot3.getKey());
                                    PlantReminderModel plantReminders = dataSnapshot3.getValue(PlantReminderModel.class);
                                    list.add(plantReminders);
                                }
                            }

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