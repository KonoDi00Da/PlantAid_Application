package com.example.plantaid_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantaid_application.Models.PlantReminderModel;
import com.example.plantaid_application.Models.User_Plants;
import com.example.plantaid_application.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReminderPlant_Adapter extends RecyclerView.Adapter<ReminderPlant_Adapter.MyViewHolder> {
    Context context;
    ArrayList<PlantReminderModel> list;

    public ReminderPlant_Adapter(ArrayList<PlantReminderModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderPlant_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_plant_reminder_1,parent,false);
        return new ReminderPlant_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderPlant_Adapter.MyViewHolder holder, int position) {
        //assigning the values to the recyclerview
        PlantReminderModel model = list.get(position);

        holder.txtTask.setText(model.getReminderType());
        holder.txtTime.setText(model.getTime().replace("_"," "));
        holder.txtDate.setText(model.getDate().replace("_"," "));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing views from the recyclerview layout
        //similar to onCreate method

        TextView txtTask, txtTime, txtDate;
        FloatingActionButton btnEdit, btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTask = itemView.findViewById(R.id.txtTask);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtDate = itemView.findViewById(R.id.txtDate);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete1);

        }
    }
}
