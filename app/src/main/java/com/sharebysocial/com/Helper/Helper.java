package com.sharebysocial.com.Helper;

import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Helper {
    public void hideBar(AppCompatActivity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int replace) {
        // "Replace" is the container with which the new  fragment you want to replace
        // "fragment" is the Fragment which you want to replace

        fragmentManager.beginTransaction()
                .replace(replace, fragment)
                .addToBackStack(null)
                .commit();

    }


}
