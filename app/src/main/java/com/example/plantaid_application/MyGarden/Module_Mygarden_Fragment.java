package com.example.plantaid_application.MyGarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.Models.PlantListModel;
import com.example.plantaid_application.Models.User_MyGarden_Adapter;
import com.example.plantaid_application.Models.User_Plants;
import com.example.plantaid_application.Models.User_Plants_RV;
import com.example.plantaid_application.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Module_Mygarden_Fragment extends Fragment {
    Add_Plant_Details add_plant_details = new Add_Plant_Details();

    private FloatingActionButton floatingActionButton;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private User_MyGarden_Adapter cAdapter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<User_Plants> list = new ArrayList<>();
    private int dataCount;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_module__mygarden, container, false);
        return myView;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.txtNoPlants);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module_Mygarden_Fragment.this.getActivity(), Add_Plant.class);
                startActivity(intent);

            }

        });

        try{
//            homeLoading = view.findViewById(R.id.loadingScreenHome);
//            homeNoInternet = view.findViewById(R.id.noInternetHome);

            // Add the following lines to create RecyclerView
            recyclerView = view.findViewById(R.id.myGardenRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setHasFixedSize(true);
            cAdapter = new User_MyGarden_Adapter(list, getActivity());
            recyclerView.setAdapter(cAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("myGarden");
//            databaseReference.child("Users").keepSynced(true);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        textView.setVisibility(View.GONE);
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            User_Plants userPlants = dataSnapshot.getValue(User_Plants.class);
                            list.add(userPlants);
                        }
                        cAdapter.notifyDataSetChanged();
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    toast("Error");

                }
            });

        } catch (Exception e){
            Log.e("Homescreen", "exception", e);
        }

    }
    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}