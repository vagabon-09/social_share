package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.R;

public class FriendViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);
        Helper.hideBar(this);
    }
}