package com.example.plantaid_application.MyGarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantaid_application.Adapter.ReminderPlant_Adapter;
import com.example.plantaid_application.Adapter.TabLayoutAdapter;
import com.example.plantaid_application.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class Module_MyGarden_Details extends AppCompatActivity {

    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private TabLayoutAdapter tabLayoutAdapter;
    public String plantKey, userKey, commonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_my_garden_details);
        imageView = findViewById(R.id.imageView5);

        Intent intent = getIntent();
        plantKey = intent.getStringExtra("plantKey");
        userKey = intent.getStringExtra("userKey");
        commonName = intent.getStringExtra("commonName");

        String plantCom = commonName;

        Picasso.get().load(getIntent().getStringExtra("plant_image"))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);

        tabLayoutAdapter = new TabLayoutAdapter(this);
        viewPager2.setAdapter(tabLayoutAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }

    public String getPlantKey() {
        return plantKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getCommonName() { return commonName; }
}