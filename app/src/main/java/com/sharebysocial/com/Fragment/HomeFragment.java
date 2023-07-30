package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.ProfileAdapter;
import com.sharebysocial.com.Algorithm.NameFormation;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private ProfileAdapter adapter;
    private ImageView profileImage;
    private TextView userName;
    private EditText searchBar;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.profileRecyclerViewId); // connecting recyclerview with ui
        searchBar = view.findViewById(R.id.homeSearchId); // connecting searchbar with ui

        fetchAccountData(view); // fetching all firebase data and setting in recycler view
        updateHomePage(view);// Updating home page ui like , name images both are edited using the function
        setButton(view); // all button click action are inside this function
        searchBar();// search implement function

        return view;
    }

    private void fetchAccountData(View view) {
        // getting reference of firebase data base
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull(mAuth.getUid())).child("ProfileInformation");
        // setting linear layout to recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // fetching data form firebase
        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(databaseReference, ProfileModel.class).build();
        // setting options to adapter class
        adapter = new ProfileAdapter(options);
        // setting adapter to recycler view
        recyclerView.setAdapter(adapter);
    }

    private void searchBar() {
        // listener of edit text and according to the search
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString()); // this function filter all recycler view result
            }
        });
    }

    private void filter(String s) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull(mAuth.getUid())).child("ProfileInformation");
        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(databaseReference.orderByChild("accountName").startAt(s).endAt(s + "\uf8ff"), ProfileModel.class).build();
        adapter = new ProfileAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void setButton(View view) {
        view.findViewById(R.id.qr_btn_id).setOnClickListener(v -> {
            QrFragment bottomSheetFragment = new QrFragment();
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
        });

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
