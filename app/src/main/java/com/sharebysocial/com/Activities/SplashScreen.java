package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sharebysocial.com.Helper.AnimationR;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.databinding.ActivitySplashBinding;

public class SplashScreen extends AppCompatActivity {
    private AnimationR animationR;
    private ActivitySplashBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        animationR = new AnimationR();
        mAuth = FirebaseAuth.getInstance();

        /*
         *   Using this function we are removing status bar (Status bar is present in the top of the app
         *   and here we can see battery status , network status and others notifications)
         */
        Helper.hideBar(this);
        /*
         * In this function we are setting all animation for the splash screen
         */
        animate();
        /*
         *In this function we will write code to redirect activity to home activity
         */
        goToHome();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
        if (currentUser != null) {
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void goToHome() {
        int postDelayed = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, postDelayed);
    }

    private void animate() {
        animationR.rightZoomOut(binding.instagramIconId, 500);
        animationR.leftZoomOut(binding.facebookIconId, 1000);
        animationR.leftZoomOut(binding.snapchatIconId, 1500);
        animationR.leftZoomOut(binding.githubIconId, 2000);
        animationR.rightZoomOut(binding.linkedinIconId, 2500);
        animationR.rightZoomOut(binding.twitterIconId, 3000);

    }


}