package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.FriendSMAdapter;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

public class FriendViewActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView friendRecView;
    private  FriendSMAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);
        setView(); // Setting all view with the layout
        Helper.hideBar(this); // Hiding status bar
        fetchingFirebase(); // Fetching firebase data and showing in recyclerview
    }

    private void setView() {
        friendRecView = findViewById(R.id.friend_recyclerView);
    }

    private void fetchingFirebase() {

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userAuthId");
        reference = FirebaseDatabase.getInstance().getReference().child(userId).child("ProfileInformation");
        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(reference, ProfileModel.class).build();
//        Log.d("userAuthId", "fetchingFirebase: "+userId);
        friendRecView.setLayoutManager(new GridLayoutManager(this,3));
       adapter = new FriendSMAdapter(options);
        friendRecView.setAdapter(adapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}


