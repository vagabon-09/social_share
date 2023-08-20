package com.sharebysocial.com.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.FriendSMAdapter;
import com.sharebysocial.com.Fragment.FriendPageInfo;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendViewActivity extends AppCompatActivity {
    private RecyclerView friendRecView;
    private FriendSMAdapter adapter;
    private static CircleImageView profileImg;
    private TextView greeting;
    private ImageView backBtn, activityInfo;

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
        activityInfo = findViewById(R.id.friend_profile_infoBtn);
    }

    public void setButtonClick() {
        backBtn.setOnClickListener(v -> finish());

        activityInfo.setOnClickListener(v -> {
            FriendPageInfo friendPageInfo = new FriendPageInfo();
            friendPageInfo.show(this.getSupportFragmentManager(), friendPageInfo.getTag());
            friendPageInfo.setCancelable(true);
        });
    }


    private void fetchingFirebase() {
        Intent intent = getIntent();
        String profileId = intent.getStringExtra("profileId");
        String userId = intent.getStringExtra("userAuthId");
        String userName = intent.getStringExtra("userName");
        String profileUrl = intent.getStringExtra("profileImg");

        Log.d("userDetails", "fetchingFirebase: " + userName);
        Log.d("userDetails", "fetchingFirebase: " + profileUrl);
        if (userName != null || profileUrl != null) {
            Glide.with(this).load(profileUrl).into(profileImg);
            String name = "I am " + userName;
            greeting.setText(name);
        }


        FirebaseRecyclerOptions<ProfileModel> options = null;
        DatabaseReference reference;
        Log.d("profileIdDebugFirebase", "fetchingFirebase: " + profileId);
        if (profileId == null) {
            // if the page is not opened through qr code then this portion of the function  will work
            try {
                reference = FirebaseDatabase.getInstance().getReference().child(userId).child("ProfileInformation");
                options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(reference, ProfileModel.class).build();
            } catch (Exception e) {
                Toast.makeText(this, "Please Scan a valid qr code", Toast.LENGTH_SHORT).show();
                Log.d("validQR", "fetchingFirebase: " + e);
            }

        } else {
            // if the page is opened through qr code then this portion of the function will work
            try {
                reference = FirebaseDatabase.getInstance().getReference().child(profileId).child("ProfileInformation");
                setUi(profileId);
                options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(reference, ProfileModel.class).build();
            } catch (Exception e) {
                Toast.makeText(this, "Please scan a valid qr for this application", Toast.LENGTH_SHORT).show();
                Log.d("validQR", "fetchingFirebase: " + e);
            }

        }

        friendRecView.setLayoutManager(new GridLayoutManager(this, 3));
        assert options != null;
        adapter = new FriendSMAdapter(options);
        friendRecView.setAdapter(adapter);
    }

    public void setUi(String profileId) { // this function is setting image and name to the friend view when some one scan qr code
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(profileId).child("userProfileData").child("0").get().addOnSuccessListener(dataSnapshot -> {
            String name = dataSnapshot.child("userName").getValue().toString();
            String imgUrl = dataSnapshot.child("userImage").getValue().toString();
            Glide.with(FriendViewActivity.this).load(imgUrl).into(profileImg);
            String str = "I am  " + name;
            greeting.setText(str);
        });
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


