package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.sharebysocial.com.Adapter.TabPageAdapter;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.R;

public class QRActivity extends AppCompatActivity {
    private ViewPager qrPager;
    private TabLayout qrLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);
        Helper.hideBar(this); // hiding status bar
        qrPager = findViewById(R.id.qrPagerId);
        qrLayout = findViewById(R.id.qrTablayoutId);
        TabPageAdapter adapter = new TabPageAdapter(getSupportFragmentManager());
        qrPager.setAdapter(adapter);
        qrLayout.setupWithViewPager(qrPager);

    }
}