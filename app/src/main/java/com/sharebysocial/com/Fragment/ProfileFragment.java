package com.sharebysocial.com.Fragment;

import static android.app.Activity.RESULT_OK;
import static com.sharebysocial.com.Helper.DayNightMode.activeDayNight;
import static com.sharebysocial.com.Helper.DayNightMode.isDayNight;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sharebysocial.com.Activities.SignUpActivity;
import com.sharebysocial.com.Helper.DayNightMode;
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
    FirebaseUser firebaseUser;
    final int PICK_IMAGE_REQUEST = 1;
    String input;
    String pName;

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
        LinearLayout editProfileBtn = view.findViewById(R.id.edit_profile_id);
        ImageView editProfileImage = view.findViewById(R.id.edit_profile_img);
        ImageView editProfileName = view.findViewById(R.id.edit_profile_name);
        LinearLayout editProfileLayout = view.findViewById(R.id.updateProfileNameId);
        ImageView doneUpdate = view.findViewById(R.id.updateDoneBtnId);
        EditText updateText = view.findViewById(R.id.profileUpdateEditText);
        final int[] count = {0};
        editProfileBtn.setOnClickListener(v -> { // Edit profile button
            if (count[0] == 0) {
                count[0]++;
                editProfileImage.setVisibility(View.VISIBLE);
                editProfileName.setVisibility(View.VISIBLE);
            } else {
                count[0]--;
                editProfileImage.setVisibility(View.GONE);
                editProfileName.setVisibility(View.GONE);
            }
        });
        editProfileImage.setOnClickListener(v -> changePhoto());
        editProfileName.setOnClickListener(v -> {
            editProfileLayout.setVisibility(View.VISIBLE);
            editProfileName.setVisibility(View.GONE);
            profileName.setVisibility(View.GONE);
            input = updateText.getText().toString();
            pName = profileName.getText().toString();
            updateText.setText(pName);
        });
        doneUpdate.setOnClickListener(v -> {
            input = updateText.getText().toString();
            pName = profileName.getText().toString();
            if (input.length() != 0) {
                editProfileLayout.setVisibility(View.GONE);
            } else {
                Toast.makeText(getContext(), "Please enter Your name .", Toast.LENGTH_SHORT).show();
            }
            profileName.setVisibility(View.VISIBLE); // making profile name visible
            profileName.setText(input); // updating profile name to the textview
            updateTextFirebase(input);
        });
        updateText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = s.length();
                if (count > 0) {
                    doneUpdate.setVisibility(View.VISIBLE);
                } else {
                    doneUpdate.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    private void updateTextFirebase(String input) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(FirebaseAuth.getInstance().getUid()).child("userProfileData").child("0").child("userName").setValue(input);
    }


    private void changePhoto() {
// Inside your activity or fragment
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri image_uri = data.getData(); // getting gallery image uri
            profileImage.setImageURI(image_uri); // setting that image to image view in app
            StorageReference storageRef = FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().getUid()); // getting firebase storage location
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser(); // getting firebase user
            storageRef.putFile(image_uri).addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> { // sending that image to firebase
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child(FirebaseAuth.getInstance().getUid()).child("userProfileData").child("0").child("userImage").setValue(uri.toString())
                        .addOnSuccessListener(unused ->
                                Toast.makeText(getContext(), "Successfully Changed Profile image.", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> {
                            Toast.makeText(getContext(), "Profile Image is not changed.", Toast.LENGTH_SHORT).show();
                        });
            }));
        }
    }

    private void setView(View view) {
        shimmerFrameLayout = view.findViewById(R.id.faceBookShimmerId);
        profileImage = view.findViewById(R.id.profile_page_image_id);
        profileName = view.findViewById(R.id.profile_page_user_name_id);
        nightModeSwitch = view.findViewById(R.id.nightModeBtnId);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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
            DayNightMode.activeDayNight(true, getContext());
            Intent intent = new Intent(getContext(), SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void nightModeSetUp(View view) {
        int postDelayed = 400;
        // Accessing Button
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nightModeSwitch.setThumbIconResource(R.drawable.moon_icon);
                //Changing material switch track color
                nightModeSwitch.setTrackTintList(trackTint("#1d3964"));  // changing track color
                //Changing thumb color
                nightModeSwitch.setThumbTintList(thumbColor("#F2F2F2"));

                new Handler().postDelayed(() -> { // making delay for smooth day night button animation
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    if (!isDayNight(getContext())) {
                        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationId);
                        bottomNavigationView.setSelectedItemId(R.id.home_btn);
                    }
                    activeDayNight(true, profileImage.getContext()); // This function is used to set day night button clicked or not
                }, postDelayed);


            } else {
                nightModeSwitch.setThumbIconResource(R.drawable.sun_icon_two);
                //Changing material switch track color
                nightModeSwitch.setTrackTintList(trackTint("#1877F2")); // changing track color
                //Changing thumb color
                nightModeSwitch.setThumbTintList(thumbColor("#00000000"));

                new Handler().postDelayed(() -> {
                    if (isDayNight(getContext())) { // making delay for smooth day night button animation
                        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationId);
                        bottomNavigationView.setSelectedItemId(R.id.home_btn);
                    }
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    activeDayNight(false, profileImage.getContext()); // This function is used to set day night mode clicked or not
                }, postDelayed);

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