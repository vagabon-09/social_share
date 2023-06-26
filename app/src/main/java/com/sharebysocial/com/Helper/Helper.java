package com.sharebysocial.com.Helper;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Helper {
    public void hideBar(AppCompatActivity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int replace) {
        fragmentManager.beginTransaction()
                .replace(replace, fragment)
                .addToBackStack(null)
                .commit();
    }


}
