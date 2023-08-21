package com.sharebysocial.com.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.sharebysocial.com.Fragment.AddFragment;
import com.sharebysocial.com.Fragment.HistoryFragment;
import com.sharebysocial.com.Fragment.HomeFragment;
import com.sharebysocial.com.Fragment.ProfileFragment;
import com.sharebysocial.com.Fragment.RadarFragment;

import com.sharebysocial.com.Helper.DayNightMode;
import com.sharebysocial.com.R;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {
    private Helper helper;
    private ActivityHomeBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        DayNightMode.autoDayNight(this); //  setting night mode or day mode according to the toggle button active
        setContentView(binding.getRoot());
        helper = new Helper();
        fragmentManager = getSupportFragmentManager();
        Helper.hideBar(this);
        /*In this Function we are Replacing Fragment*/
        replaceFragment();
        /*
         * Bottom navigation bar operation
         */
        bottomNavigation();
        customiseBottomNav();
    }

    private void customiseBottomNav() {
        if (DayNightMode.isDayNight(this)) {
            binding.bottomNavigationId.setItemIconTintList(ColorStateList.valueOf(R.drawable.night_nav_selector));
        }
    }


    private void bottomNavigation() {
        binding.bottomNavigationId.setOnItemSelectedListener(item -> {
            item.setCheckable(true);
            if (item.getItemId() == R.id.home_btn) {
                helper.replaceFragment(fragmentManager, new HomeFragment(), R.id.containerId);
            } else if (item.getItemId() == R.id.radar_btn) {
                helper.replaceFragment(fragmentManager, new RadarFragment(), R.id.containerId);
            } else if (item.getItemId() == R.id.add_btn) {
                helper.replaceFragment(fragmentManager, new AddFragment(), R.id.containerId);
            } else if (item.getItemId() == R.id.profile_btn) {
                helper.replaceFragment(fragmentManager, new ProfileFragment(), R.id.containerId);
            } else if (item.getItemId() == R.id.history_btn) {
                helper.replaceFragment(fragmentManager, new HistoryFragment(), R.id.containerId);
            }
            return true;
        });
    }


    private void replaceFragment() {
        helper.replaceFragment(fragmentManager, new HomeFragment(), R.id.containerId);
    }

    @Override
    public void onBackPressed() {
        // Check if there are any fragments in the back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // If there are, pop the fragment from the back stack
            getSupportFragmentManager().popBackStack();
            binding.bottomNavigationId.setSelectedItemId(R.id.home_btn);

        } else {
            // If there are no fragments in the back stack, exit the app
            super.onBackPressed();
        }

    }

}