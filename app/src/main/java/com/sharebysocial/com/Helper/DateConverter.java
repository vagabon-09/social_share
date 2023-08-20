package com.sharebysocial.com.Helper;

import android.annotation.SuppressLint;

import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateConverter {
    @Deprecated
    public static String convertDateHistoryModel(String format, ArrayList<HistoryModel> historyModels, int position) {
        Date date = new Date(historyModels.get(position).getDate());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format); // Customize date format as needed
        return sdf.format(date);
    }

    public static String globalConvertTime(String format, long timeInMillisecond) {
        Date date = new Date(timeInMillisecond);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(format); // Customize date format as needed
        return sdf.format(date);
    }
}
