package com.sharebysocial.com.Helper;

import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateConverter {
    public String convertDate(String format, ArrayList<HistoryModel> historyModels, int position) {
        Date date = new Date(historyModels.get(position).getDate());
        SimpleDateFormat sdf = new SimpleDateFormat(format); // Customize date format as needed
        return sdf.format(date);
    }
}
