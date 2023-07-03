package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Adapter.ProfileAdapter;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;

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
        recyclerView = view.findViewById(R.id.profileRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ProfileModel> options = new FirebaseRecyclerOptions.Builder<ProfileModel>().setQuery(databaseReference, ProfileModel.class).build();
        BottomSheetfFragment fragment = new BottomSheetfFragment();
        adapter = new ProfileAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;
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
