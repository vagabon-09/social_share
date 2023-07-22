package com.sharebysocial.com.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sharebysocial.com.Fragment.QrFragment;
import com.sharebysocial.com.Fragment.ScanFragment;

public class TabPageAdapter extends FragmentPagerAdapter {
    public TabPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new QrFragment();
        }
        return new ScanFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "QR Scanner";
        } else {
            return "My QR";
        }
    }
}
