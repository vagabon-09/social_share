package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sharebysocial.com.Adapter.RadarAdapter;
import com.sharebysocial.com.Algorithm.DistanceCalculator;
import com.sharebysocial.com.Helper.InternetWarning;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.Model.RadarModel;
import com.sharebysocial.com.databinding.FragmentRadarBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RadarFragment extends Fragment {
    private FragmentRadarBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RadarAdapter adapter;
    private RadarModel radarModel;
    private FirebaseAuth mAuth;
    List<RadarModel> radarModelArrayList;

    public RadarFragment() {
        // Required empty public constructor
    }

    public static RadarFragment newInstance(String param1, String param2) {
        RadarFragment fragment = new RadarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internetCheck();
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void internetCheck() {
        if (NetworkCheck.isNetworkConnected(requireContext())) {
            InternetWarning internetWarning = new InternetWarning(requireActivity());
        } else {
            showBottomSheet();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadarBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        setView();
        binding.searchResultScanningId.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        fetchFirebase();
        return view;
    }

    private void fetchFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Getting current user latitude form db
                double current_user_lati = snapshot.child(Objects.requireNonNull(mAuth.getUid())).child("location").child("0").child("lati").getValue(Double.class);
                // Getting current user longitude from db
                double current_user_longi = snapshot.child(Objects.requireNonNull(mAuth.getUid())).child("location").child("0").child("longi").getValue(Double.class);
//                Log.d("dataSnap", "onDataChange: " + current_user_latitude + "");
                radarModelArrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataSnapshot locationSnapshot = dataSnapshot.child("location"); // getting location data form db
                    DataSnapshot userSnapshot = dataSnapshot.child("userProfileData"); // getting userProfileData form db
                    double lati = locationSnapshot.child("0").child("lati").getValue(Double.class); // getting latitude from db
                    double longi = locationSnapshot.child("0").child("longi").getValue(Double.class); // getting longitude from db
                    String userName = userSnapshot.child("0").child("userName").getValue(String.class); // getting userName from db
                    String userImage = userSnapshot.child("0").child("userImage").getValue(String.class); // getting userImage form database
                    String userId = dataSnapshot.getKey(); // taking user id from firebase

//                    Log.d("userDistance", "onDataChange: " + DistanceCalculator.calculateDistance(current_user_lati, current_user_longi, lati, longi) + "");
                    if (!Objects.equals(mAuth.getUid(), userId) && DistanceCalculator.calculateDistance(current_user_lati, current_user_longi, lati, longi) <= 500) {
                        radarModel = new RadarModel(userName, userImage, userId, lati, longi); // Setting data to radar model
                        radarModelArrayList.add(radarModel); // Setting model to arraylist
                    }
                }
                adapter = new RadarAdapter(radarModelArrayList, getContext()); // creating object of adapter modal
                binding.searchResultScanningId.setAdapter(adapter); // setting adapter to recyclerview
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void showBottomSheet() {
        SearchSheetFragment searchSheetFragment = new SearchSheetFragment();
        searchSheetFragment.show((((AppCompatActivity) requireContext()).getSupportFragmentManager()), searchSheetFragment.getTag());
        searchSheetFragment.setCancelable(false);
    }

    private void setView() {
        binding.radarAnimationId.playAnimation();
    }
}