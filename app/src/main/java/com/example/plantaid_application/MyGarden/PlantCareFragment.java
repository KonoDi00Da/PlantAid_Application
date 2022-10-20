package com.example.plantaid_application.MyGarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plantaid_application.Module_Identify_Plant_Result;
import com.example.plantaid_application.R;

public class PlantCareFragment extends Fragment {
    CardView cardCustom, cardWater, cardFertilize, cardRepot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_plant_care, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardCustom = view.findViewById(R.id.cardCustom);
        cardRepot = view.findViewById(R.id.cardRepot);
        cardWater = view.findViewById(R.id.cardWater);
        cardFertilize = view.findViewById(R.id.cardFertilize);

        Intent intent = new Intent(getActivity(), PlantCare_Add_Reminder.class);

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



    }
}