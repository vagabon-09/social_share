package com.sharebysocial.com.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.sharebysocial.com.Fragment.AddFragment;
import com.sharebysocial.com.Fragment.HistoryFragment;
import com.sharebysocial.com.Fragment.HomeFragment;
import com.sharebysocial.com.Fragment.ProfileFragment;
import com.sharebysocial.com.Fragment.RadarFragment;
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
        setContentView(binding.getRoot());
        helper = new Helper();
        fragmentManager = getSupportFragmentManager();
        /* This function is going to use all helper class functions which are needed */
        helperClass();
        /*In this Function we are Replacing Fragment*/
        replaceFragment();
        /*
         * Bottom navigation bar operation
         *
         */
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.bottomNavigationId.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }

    private void replaceFragment() {
        helper.replaceFragment(fragmentManager, new HomeFragment(), R.id.containerId);
    }

    private void helperClass() {
        helper.hideBar(this);
    }
}