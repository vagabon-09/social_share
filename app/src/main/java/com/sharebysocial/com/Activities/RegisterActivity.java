package com.sharebysocial.com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView alreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Helper.hideBar(RegisterActivity.this);
        findViewsById();
        clickListener();
    }

    private void findViewsById() {
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccountId);
    }

    private void clickListener() {
        alreadyHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this,SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }
}