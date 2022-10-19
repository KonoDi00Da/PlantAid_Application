package com.example.plantaid_application.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.plantaid_application.MyGarden.PlantCareFragment;
import com.example.plantaid_application.MyGarden.PlantInfoFragment;
import com.example.plantaid_application.MyGarden.ProgressFragment;

public class TabLayoutAdapter extends FragmentStateAdapter {
    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProgressFragment();
            case 1:
                return new PlantCareFragment();
            case 2:
                return new PlantInfoFragment();
            default:
                return new ProgressFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
