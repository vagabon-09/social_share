package com.sharebysocial.com.Helper;

import android.app.Activity;
import android.view.Gravity;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

public class InternetWarning {
    public InternetWarning(Activity activity) {
        new AestheticDialog.Builder(activity, DialogStyle.FLAT, DialogType.ERROR)
                .setTitle("Internet")
                .setMessage("Check your Internet Connection")
                .setCancelable(false)
                .setDarkMode(false)
                .setGravity(Gravity.CENTER)
                .setAnimation(DialogAnimation.SHRINK)
                .setOnClickListener(AestheticDialog.Builder::dismiss)
                .show();
    }
}
