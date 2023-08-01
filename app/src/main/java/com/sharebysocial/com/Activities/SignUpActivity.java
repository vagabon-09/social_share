package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharebysocial.com.Helper.InternetWarning;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.R;

public class SignUpActivity extends AppCompatActivity {
    TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internetCheck();
        setContentView(R.layout.activity_sign_up);
        findViewsById();
        clickListener();
    }

    public void internetCheck() {
        if (NetworkCheck.isNetworkConnected(this)) {
            InternetWarning internetWarning = new InternetWarning(this);
        }
    }

    private void clickListener() {
        noAccount.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void findViewsById() {
        noAccount = findViewById(R.id.noAccountId);
    }


}