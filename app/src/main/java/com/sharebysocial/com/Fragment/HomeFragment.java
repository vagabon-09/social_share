package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.ProfileAdapter;
import com.sharebysocial.com.Algorithm.NameFormation;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.Model.UserModel;
import com.sharebysocial.com.R;

import java.net.URI;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ProfileAdapter adapter;
    private ImageView profileImage;
    private TextView userName;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull(mAuth.getUid())).child("ProfileInformation");
        RecyclerView recyclerView = view.findViewById(R.id.profileRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(databaseReference, ProfileModel.class).build();
        BottomSheetfFragment fragment = new BottomSheetfFragment();
        adapter = new ProfileAdapter(options);
        recyclerView.setAdapter(adapter);
        // Updating home page ui like , name images both are edited using the function
        updateHomePage(view);
        return view;
    }

    private void updateHomePage(View view) {
        profileImage = view.findViewById(R.id.profile_image_id);
        userName = view.findViewById(R.id.user_name_id);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .child("userProfileData")
                .child("0");

        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            String userName_s = Objects.requireNonNull(dataSnapshot.child("userName").getValue()).toString();
            String uri = Objects.requireNonNull(dataSnapshot.child("userImage").getValue()).toString();
            Glide.with(requireContext()).load(uri).into(profileImage);

//                Log.d("UserDetails", "onSuccess: "+userName_s);
            /*
             * Here getShortName is a function which make the string in a short term like
             * Rajesh Bhadra = Rajesh B...
             */
            userName.setText(NameFormation.getShortName(userName_s));
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
