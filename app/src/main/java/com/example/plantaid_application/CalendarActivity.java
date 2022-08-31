package com.example.plantaid_application;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
    }
}
