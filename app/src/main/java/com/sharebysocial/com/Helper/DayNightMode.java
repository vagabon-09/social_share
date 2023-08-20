package com.sharebysocial.com.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;


public class DayNightMode {
    public static void activeDayNight(boolean status, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("nightMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isNight", status);
        editor.apply();
    }

    public static boolean isDayNight(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("nightMode", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isNight", false);
    }

    public static void autoDayNight(Context context){
        if (DayNightMode.isDayNight(context)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
}
