package com.sharebysocial.com.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.sharebysocial.com.Model.UserModel;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Query("SELECT * FROM HistoryDB")
    List<HistoryModel> getAllHistory();

    @Insert
    void addHistory(HistoryModel historyModel);

    @Query("DELETE FROM HistoryDB WHERE id = :id")
    void deleteHistory(int id);

}
