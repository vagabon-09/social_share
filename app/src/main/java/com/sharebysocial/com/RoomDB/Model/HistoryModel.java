package com.sharebysocial.com.RoomDB.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "HistoryDB")
public class HistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Name")
    private String userName;
    @ColumnInfo(name = "ImgUrl")
    private String userImage;
    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "date")
    private long date;

//    HistoryModel(int id, String userName, String userImage, String userId) {
//        this.id = id;
//        this.userName = userName;
//        this.userId = userId;
//        this.userImage = userImage;
//    }


    public HistoryModel(String userName, String userImage, String userId, long date) {
        this.userName = userName;
        this.userId = userId;
        this.userImage = userImage;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
