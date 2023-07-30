package com.sharebysocial.com.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.sharebysocial.com.Fragment.AddFragment;
import com.sharebysocial.com.Fragment.HistoryFragment;
import com.sharebysocial.com.Fragment.HomeFragment;
import com.sharebysocial.com.Fragment.ProfileFragment;
import com.sharebysocial.com.Fragment.RadarFragment;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.R;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.databinding.ActivityHomeBinding;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

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
        Helper.hideBar(this);
        /*In this Function we are Replacing Fragment*/
        replaceFragment();
        /*
         * Bottom navigation bar operation
         */
        bottomNavigation();
        netCheck();
    }

    public void netCheck() {
        if (!NetworkCheck.isNetworkConnected(this)) {
            setWarning();
        }
    }

    private void setWarning() {
        new AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.ERROR)
                .setTitle("Internet")
                .setMessage("Check your Internet Connection")
                .setCancelable(false)
                .setDarkMode(false)
                .setGravity(Gravity.CENTER)
                .setAnimation(DialogAnimation.SHRINK)
                .setOnClickListener(AestheticDialog.Builder::dismiss)
                .show();
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
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.bottomNavigationId);
        if (currentFragment instanceof HomeFragment) {
            // Handle back button press when in HomeFragment
            // For example, navigate to a different fragment or exit the app
            super.onBackPressed();
        } else {
            // If not in HomeFragment, navigate to HomeFragment on back button press
            binding.bottomNavigationId.setSelectedItemId(R.id.home_btn);

        }
    }

}