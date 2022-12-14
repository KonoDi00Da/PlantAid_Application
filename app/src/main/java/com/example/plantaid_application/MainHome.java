package com.example.plantaid_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.plantaid_application.MyGarden.Module_Mygarden_Fragment;
import com.example.plantaid_application.databinding.ActivityMainBinding;

public class MainHome extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new Module_Today()).commit();

        //clicking on menu item
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.navigation_today:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new Module_Today()).addToBackStack(null).commit();
                    break;
                case R.id.navigation_garden:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new Module_Mygarden_Fragment()).addToBackStack(null).commit();
                    break;
                case R.id.navigation_identify:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new Module_Identify_Plant()).addToBackStack(null).commit();
                    break;
                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, new Module_Settings()).addToBackStack(null).commit();
                    break;
            }

            return true;
        });

    }




}
