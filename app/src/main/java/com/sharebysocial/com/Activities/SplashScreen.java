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
         *In this function we will write code to redirect activity to home activity
         */
//        goToHome();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
        if (currentUser != null) {
            goToHome();
        } else {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void goToHome() {
        int postDelayed = 3000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }, postDelayed);
    }




}