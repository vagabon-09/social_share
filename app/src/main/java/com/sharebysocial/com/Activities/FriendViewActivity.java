package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.FriendSMAdapter;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.Model.AccountModel;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendViewActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView friendRecView;
    private FriendSMAdapter adapter;
    private String profileId = "";
    private CircleImageView profileImg;
    private TextView greeting;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);
        setView(); // Setting all view with the layout
        Helper.hideBar(this); // Hiding status bar
        fetchingFirebase(); // Fetching firebase data and showing in recyclerview
        setButtonClick();

    }

    private void setView() {
        friendRecView = findViewById(R.id.friend_recyclerView);
        profileImg = findViewById(R.id.friend_profile_imageView);
        greeting = findViewById(R.id.friend_profile_greeting);
        backBtn = findViewById(R.id.friend_profile_backBtn);
    }

    public void setButtonClick() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchingFirebase() {
        Intent intent = getIntent();
        profileId = intent.getStringExtra("profileId");
        String userId = intent.getStringExtra("userAuthId");
        String userName = intent.getStringExtra("userName");
        String profileUrl = intent.getStringExtra("profileImg");

        Log.d("userDetails", "fetchingFirebase: " + userName);
        Log.d("userDetails", "fetchingFirebase: " + profileUrl);
        if (userName != null || profileUrl != null) {
            Glide.with(this).load(profileUrl).into(profileImg);
            greeting.setText("I am " + userName);
        }


        FirebaseRecyclerOptions<ProfileModel> options = null;
        if (profileId == null) {
            try {
                reference = FirebaseDatabase.getInstance().getReference().child(userId).child("ProfileInformation");
                options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(reference, ProfileModel.class).build();
            } catch (Exception e) {
                Toast.makeText(this, "Please Scan a valid qr code", Toast.LENGTH_SHORT).show();
                Log.d("validQR", "fetchingFirebase: " + e.toString());
            }


        } else {
            try {
                reference = FirebaseDatabase.getInstance().getReference().child(profileId).child("ProfileInformation");
                options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(reference, ProfileModel.class).build();
            } catch (Exception e) {
                Toast.makeText(this, "Please scan a valid qr for this application", Toast.LENGTH_SHORT).show();
                Log.d("validQR", "fetchingFirebase: " + e.toString());
            }

        }

        friendRecView.setLayoutManager(new GridLayoutManager(this, 3));
        assert options != null;
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


