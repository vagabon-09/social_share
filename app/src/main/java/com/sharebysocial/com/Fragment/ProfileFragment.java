package com.sharebysocial.com.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Activities.SignUpActivity;
import com.sharebysocial.com.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialSwitch nightModeSwitch;
    private LinearLayout logoutBtn;
    private ImageView profileImage;
    private TextView profileName;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nightModeSetUp(view);
        logOutBtn(view); // It login out the user
        fetchFirebaseData(view);
        return view;
    }

    private void fetchFirebaseData(View view) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("userProfileData").child("0")
                .get().addOnSuccessListener(dataSnapshot -> setUi(dataSnapshot, view));
    }

    private void setUi(DataSnapshot dataSnapshot, View view) {
        profileImage = view.findViewById(R.id.profile_page_image_id);
        profileName = view.findViewById(R.id.profile_page_user_name_id);
//        Log.d("UserProfileData", "setUi: "+dataSnapshot.toString());
        String pName = Objects.requireNonNull(dataSnapshot.child("userName").getValue()).toString();
        String pImage = Objects.requireNonNull(dataSnapshot.child("userImage").getValue()).toString();
//        Log.d("pName", "setUi: "+pName);
        Glide.with(requireContext()).load(pImage).into(profileImage);
        profileName.setText(pName);
    }


    private void logOutBtn(View view) {
        logoutBtn = view.findViewById(R.id.logOutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(); // CLear previous gmail and lout out form current email

            }
        });
    }

    private void signOut() {
        // Clear the previous sign-in state
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient signInClient = GoogleSignIn.getClient(requireActivity(), gso);
        signInClient.signOut().addOnCompleteListener(task -> {
            // After logout, the user will be able to select an email again for sign-in.
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void nightModeSetUp(View view) {
        // Accessing Button
        nightModeSwitch = view.findViewById(R.id.nightModeBtnId);
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

            }
        });

    }
}