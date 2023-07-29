package com.sharebysocial.com.RoomDB.Helper;

import android.content.Context;
import android.provider.CalendarContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sharebysocial.com.RoomDB.HistoryDAO;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

@Database(entities = HistoryModel.class, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public static final String DB_NAME = "HistoryDB";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }

    public abstract HistoryDAO historyDAO();

}
