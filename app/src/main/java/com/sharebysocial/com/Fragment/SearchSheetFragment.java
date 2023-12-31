package com.sharebysocial.com.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Helper.InternetWarning;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.Model.LocationModel;
import com.sharebysocial.com.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchSheetFragment extends BottomSheetDialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private FusedLocationProviderClient fusedLocationProviderClient;

    public SearchSheetFragment() {
        // Required empty public constructor
    }


    public static SearchSheetFragment newInstance(String param1, String param2) {
        SearchSheetFragment fragment = new SearchSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void internetCheck() {
        if (NetworkCheck.isNetworkConnected(requireContext())) {
            InternetWarning internetWarning = new InternetWarning(requireActivity());
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_sheet, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        requestLocationPermission();

        return v;
    }

    private void requestLocationPermission() {
        // Check if the permission is granted
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        } else {
            // Permission is already granted, you can proceed with using location
//            Toast.makeText(getContext(), "Already permission granted", Toast.LENGTH_SHORT).show();
            getLastLocation();
        }
    }


    private void getLastLocation() {
        /* This function is getting current latitude and longitude and current time of that user and sending to database */
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            double latitude = 0;
                            double longitude = 0;
                            assert addresses != null;
                            latitude = addresses.get(0).getLatitude();
                            longitude = addresses.get(0).getLongitude();
                            long currentTime = System.currentTimeMillis();
                            Log.d("searchSheetDebug", "getLastLocation: outside");

                            if (latitude != 0 && longitude != 0) {
                                Log.d("searchSheetDebug", "getLastLocation: inside");
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("location");
                                List<LocationModel> locationModelList = new ArrayList<>();
                                locationModelList.add(new LocationModel(latitude, longitude, currentTime));
                                reference.setValue(locationModelList).addOnSuccessListener(unused -> dismiss());
                            }

                        } catch (IOException e) {
                            Log.d("searchException", "getLastLocation: " + e);
                        }
                    }
                });

    }
}