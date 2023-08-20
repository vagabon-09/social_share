package com.sharebysocial.com.Fragment;

import static com.sharebysocial.com.Helper.DayNightMode.activeDayNight;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Activities.SignUpActivity;
import com.sharebysocial.com.Helper.DayNightMode;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.Helper.InternetWarning;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.R;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CircleImageView profileImage;
    private TextView profileName;
    private ShimmerFrameLayout shimmerFrameLayout;
    MaterialSwitch nightModeSwitch;

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

        internetCheck();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setView(view);
        fetchFirebaseData(view);
        setButton(view);

        nightModeSetUp(view);
        nightMode();

        logOutBtn(view); // It login out the user
        return view;
    }

    private void nightMode() {
        // if night mode active then night mode button checked if not clicked then set unchecked
        nightModeSwitch.setChecked(DayNightMode.isDayNight(profileImage.getContext()));
        DayNightMode.autoDayNight(profileImage.getContext());
    }

    private void setButton(View view) {

    }

    private void setView(View view) {
        shimmerFrameLayout = view.findViewById(R.id.faceBookShimmerId);
        profileImage = view.findViewById(R.id.profile_page_image_id);
        profileName = view.findViewById(R.id.profile_page_user_name_id);
        nightModeSwitch = view.findViewById(R.id.nightModeBtnId);
    }

    private void fetchFirebaseData(View view) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("userProfileData").child("0")
                .get().addOnSuccessListener(dataSnapshot -> setUi(dataSnapshot, view));
    }

    public void internetCheck() {
        if (NetworkCheck.isNetworkConnected(requireContext())) {
            InternetWarning internetWarning = new InternetWarning(requireActivity());
        }
    }

    private void setUi(DataSnapshot dataSnapshot, View view) {
        profileImage = view.findViewById(R.id.profile_page_image_id);
        profileName = view.findViewById(R.id.profile_page_user_name_id);
//        Log.d("UserProfileData", "setUi: "+dataSnapshot.toString());
        String pName = Objects.requireNonNull(dataSnapshot.child("userName").getValue()).toString();
        String pImage = Objects.requireNonNull(dataSnapshot.child("userImage").getValue()).toString();
//        Log.d("pName", "setUi: "+pName);

        if (getContext() instanceof FragmentActivity) { // this if condition is checking is fragment attached with fragment
            Glide.with(requireContext()).load(pImage).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    shimmerFrameLayout.setVisibility(View.GONE);
                    profileImage.setVisibility(View.VISIBLE);
                    profileName.setVisibility(View.VISIBLE);
                    return false;
                }
            }).into(profileImage);
        }


        profileName.setText(pName);
    }


    private void logOutBtn(View view) {
        LinearLayout logoutBtn = view.findViewById(R.id.logOutBtn);
        logoutBtn.setOnClickListener(v -> {
            signOut(); // CLear previous gmail and lout out form current email

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
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nightModeSwitch.setThumbIconResource(R.drawable.moon_icon);
                //Changing material switch track color
                nightModeSwitch.setTrackTintList(trackTint("#1877F2")); // changing track color
                //Changing thumb color
                nightModeSwitch.setThumbTintList(thumbColor("#F2F2F2"));
                activeDayNight(true, profileImage.getContext()); // This function is used to set day night button clicked or not
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                nightModeSwitch.setThumbIconResource(R.drawable.sun_icon_two);
                //Changing material switch track color
                nightModeSwitch.setTrackTintList(trackTint("#FA0404")); // Changing track color
                //Changing thumb color
                nightModeSwitch.setThumbTintList(thumbColor("#00000000"));
                activeDayNight(false, profileImage.getContext()); // This function is used to set day night mode clicked or not
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

    }


    // This function is converting Haxe color code into ColorState list(mainly used for material switch track color)
    private ColorStateList trackTint(String color) {
        int trackColor = Color.parseColor(color); // Replace with your desired color
        return ColorStateList.valueOf(trackColor);
    }

    // This function is converting Haxe color code into ColorState list (mainly use in thumb color)
    private ColorStateList thumbColor(String color) {
        int thumbColor = Color.parseColor(color); // Replace with your desired color
        return ColorStateList.valueOf(thumbColor);
    }
}