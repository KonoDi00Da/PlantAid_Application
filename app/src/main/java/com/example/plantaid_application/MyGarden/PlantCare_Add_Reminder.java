package com.example.plantaid_application.MyGarden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plantaid_application.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlantCare_Add_Reminder extends AppCompatActivity {

    TextView txtComPlantName, txtTask, txtCalendarDate, txtTime;
    EditText txtCustomTask;
    FloatingActionButton btnBack, btnAdd;
    Button btnOK, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care_add_reminder);

        txtComPlantName = findViewById(R.id.txtComPlantName);
        txtTask = findViewById(R.id.txtTask);
        txtCalendarDate = findViewById(R.id.txtCalendarDate);
        txtTime = findViewById(R.id.txtTime);
        txtCustomTask = findViewById(R.id.txtCustomTask);
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);

        String task = getIntent().getStringExtra("taskReminder");

        if(task.equals("Custom")){
            txtCustomTask.setVisibility(View.VISIBLE);
        }else{
            txtTask.setText(task);
            txtTask.setVisibility(View.VISIBLE);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtCalendarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });


    }

    private void setTime() {

    }

    private void openCalendar() {
        Dialog dialog = new Dialog(this);
//        //We have added a title in the custom layout. So let's disable the default title.
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
//        dialog.setCancelable(true);
//        //Mention the name of the layout of your custom dialog.
//        dialog.setContentView(R.layout.calendar_view);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.calendar_view);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnOK = dialog.findViewById(R.id.btnOK);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}