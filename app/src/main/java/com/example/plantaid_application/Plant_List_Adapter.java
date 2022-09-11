package com.example.plantaid_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Plant_List_Adapter extends RecyclerView.Adapter<Plant_List_Adapter.MyViewHolder> {
    Context context;
    ArrayList<PlantModel> plantArrayList;

    public Plant_List_Adapter(Context context, ArrayList<PlantModel> plantArrayList){
        this.context = context;
        this.plantArrayList = plantArrayList;
    }

    @NonNull
    @Override
    public Plant_List_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where we'll be inflating the recyclerview (looks of the row)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new Plant_List_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Plant_List_Adapter.MyViewHolder holder, int position) {
        //assigning the values to the recyclerview
        holder.commonName.setText(plantArrayList.get(position).getCommonPlantName());
        holder.sciName.setText(plantArrayList.get(position).getSciPlantName());
        holder.imageView.setImageResource(plantArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return plantArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing views from the recyclerview layout
        //similar to onCreate method

        ImageView imageView;
        TextView commonName, sciName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            commonName = itemView.findViewById(R.id.plantCommonName);
            sciName = itemView.findViewById(R.id.plantScientificName);
        }
    }
}
