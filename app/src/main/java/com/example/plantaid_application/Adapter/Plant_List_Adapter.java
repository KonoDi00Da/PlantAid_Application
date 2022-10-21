package com.example.plantaid_application.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantaid_application.Models.PlantListModel;
import com.example.plantaid_application.MyGarden.Add_Plant_Details;
import com.example.plantaid_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Plant_List_Adapter extends RecyclerView.Adapter<Plant_List_Adapter.MyViewHolder> {

    Context context;
    ArrayList<PlantListModel> list;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    

    public Plant_List_Adapter(ArrayList<PlantListModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Plant_List_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where we'll be inflating the recyclerview (looks of the row)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Plant_List_Adapter.MyViewHolder holder, int position) {
        //assigning the values to the recyclerview
        PlantListModel model = list.get(position);

        Picasso.get().load(model.getImage()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imageView);
        holder.commonName.setText(model.getCommonName());
        holder.sciName.setText(model.getSciName());
        //holder.setListener((view, po))


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Add_Plant_Details.class);
                intent.putExtra("plant_image",model.getImage());
                intent.putExtra("com_plant", model.getCommonName());
                intent.putExtra("sci_plant", model.getSciName());
                intent.putExtra("txtWater", model.getWater());
                intent.putExtra("txtHarvest", model.getHarvest());
                intent.putExtra("txtCare", model.getCare());
                intent.putExtra("plant_desc",model.getDescription());
                intent.putExtra("txtPestsDisease",model.getPestsDiseases());
                intent.putExtra("ytLink",model.getYtLink());
                intent.putExtra("key",model.getKey());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (recyclerViewInterface != null){
//                        int pos = getAdapterPosition();
//
//                        if(pos != RecyclerView.NO_POSITION){
//                            recyclerViewInterface.onItemClick(pos);
//                        }
//
//                    }
//                }
//            });
        }

        public void setListener() {
        }
    }
}
