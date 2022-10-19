package com.example.plantaid_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantaid_application.Models.FetchModel;
import com.example.plantaid_application.Models.PlantIdentifyModel;
import com.example.plantaid_application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlantIdResultsAdapter extends RecyclerView.Adapter<PlantIdResultsAdapter.ViewHolder>{
    ArrayList<PlantIdentifyModel> resultsList;
    private Context context;

    public PlantIdResultsAdapter(Context context, ArrayList<PlantIdentifyModel> resultsList){
        this.context = context;
        this.resultsList = resultsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_identify_plants,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtScientificName.setText(resultsList.get(position).getSciName());
        holder.txtCommonName.setText(resultsList.get(position).getComName());
        holder.txtFamily.setText(resultsList.get(position).getFamily());
        holder.txtScore.setText(resultsList.get(position).getScore());
        Picasso.get().load(resultsList.get(position).getImg1()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img1);
        Picasso.get().load(resultsList.get(position).getImg2()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img2);
        Picasso.get().load(resultsList.get(position).getImg3()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img3);
        Picasso.get().load(resultsList.get(position).getImg4()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img4);
        Picasso.get().load(resultsList.get(position).getImg5()).placeholder(R.drawable.ic_launcher_foreground).into(holder.img5);


    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtScientificName, txtCommonName, txtFamily, txtScore;
        ImageView img1, img2, img3, img4, img5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtScientificName = itemView.findViewById(R.id.txtScientificName);
            txtCommonName = itemView.findViewById(R.id.txtCommonName);
            txtFamily = itemView.findViewById(R.id.txtFamily);
            txtScore = itemView.findViewById(R.id.txtScore);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            img5 = itemView.findViewById(R.id.img5);

        }
    }
}
