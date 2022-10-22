package com.example.plantaid_application.MyGarden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantaid_application.Models.PlantReminderModel;
import com.example.plantaid_application.Models.User_Plants;
import com.example.plantaid_application.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PlantCare_Add_Reminder extends AppCompatActivity {

    private TextView txtComPlantName, txtTask, txtCalendarDate, txtTime;
    private EditText edittxtCustomTask;
    private FloatingActionButton btnBack, btnAdd;
    private Button btnOK, btnCancel;
    String customTask;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;

    private int hour, minute;

    private String timeFormat,commonName, userKey, task, date, dateFormatted;

    @TimeFormat  private int clockFormat;
    @Nullable private Integer timeInputMode;


    private final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care_add_reminder);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        txtComPlantName = findViewById(R.id.txtComPlantName);
            txtTask = findViewById(R.id.txtTask);
            txtCalendarDate = findViewById(R.id.txtCalendarDate);
            txtTime = findViewById(R.id.txtTime);
            edittxtCustomTask = findViewById(R.id.txtCustomTask);
            btnBack = findViewById(R.id.btnBack);
            btnAdd = findViewById(R.id.btnAdd);

            String date_ = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
            date = new SimpleDateFormat("MMM_dd,_yyyy", Locale.getDefault()).format(new Date());
            txtCalendarDate.setText(date_);
            String timeFormat_ = "08:00 AM";
            timeFormat = "08:00_AM";
            txtTime.setText(timeFormat_);

            String userTask = getIntent().getStringExtra("taskReminder");
            commonName = getIntent().getStringExtra("plantCommonName");
            userKey = getIntent().getStringExtra("userKey");

            txtComPlantName.setText(commonName);

            clockFormat = TimeFormat.CLOCK_12H;

            if(userTask.equals("Custom")){
                edittxtCustomTask.setVisibility(View.VISIBLE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customTask = edittxtCustomTask.getText().toString().trim();
                        task = customTask;
                        if (customTask.isEmpty()) {
                            customTask = edittxtCustomTask.getText().toString().trim();
                            edittxtCustomTask.setError("Text is required");
                            edittxtCustomTask.requestFocus();
                            return;
                        }
                        addToFirebase();
                        finish();
                    }
                });
            }else{
                task = userTask;
                txtTask.setText(task);
                txtTask.setVisibility(View.VISIBLE);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addToFirebase();
                        finish();
                    }
                });
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
                    materialDatePicker();
                }
            });

            txtTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTime();
                }
            });
    }

    private void addToFirebase() {
        DatabaseReference userRef = database.getReference("Users").child(currentUser.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String plantID = "myGarden";
                    String reminders = "plantReminders";
                    String pushKey = userRef.push().getKey();
                    PlantReminderModel plantReminderModel = new PlantReminderModel(commonName,task,date,timeFormat, userKey, pushKey);
                    userRef.child(plantID).child(userKey).child(reminders).child(pushKey).setValue(plantReminderModel);
                    toast("Reminder is now set");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void materialDatePicker(){
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now());

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker().setCalendarConstraints(constraintBuilder.build());
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        final MaterialDatePicker materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date = materialDatePicker.getHeaderText().replace(" ","_");
                txtCalendarDate.setText(materialDatePicker.getHeaderText());
            }
        });
    }

    private void setTime() {
        MaterialTimePicker.Builder materialTimePickerBuilder = new MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(hour)
                .setMinute(minute);

        if (timeInputMode != null) {
            materialTimePickerBuilder.setInputMode(timeInputMode);
        }

        MaterialTimePicker materialTimePicker = materialTimePickerBuilder.build();
        materialTimePicker.show(getSupportFragmentManager(), "TIME_PICKER");

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newHour = materialTimePicker.getHour();
                int newMinute = materialTimePicker.getMinute();
                PlantCare_Add_Reminder.this.onTimeSet(newHour, newMinute);
            }
        });
    }

    private void onTimeSet(int newHour, int newMinute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, newHour);
        cal.set(Calendar.MINUTE, newMinute);
        cal.setLenient(false);

        String time = formatter.format(cal.getTime());
        timeFormat = time.replace(" ","_");
        txtTime.setText(time);
        hour = newHour;
        minute = newMinute;
    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void openCalendar() {
        Dialog dialog = new Dialog(this);
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