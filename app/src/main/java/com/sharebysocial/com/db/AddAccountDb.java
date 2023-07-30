package com.sharebysocial.com.db;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Model.AccountModel;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import java.util.Objects;

public class AddAccountDb {
    DatabaseReference database;
    AccountModel model;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public AddAccountDb(String accountName, String userName, Context context) {
        if (!Objects.equals(accountName, "Select Profile")) {


            model = new AccountModel(accountName, userName);
            database = FirebaseDatabase.getInstance().getReference().child(Objects.requireNonNull(mAuth.getUid())).child("ProfileInformation").child(accountName);
            database.setValue(model).addOnSuccessListener(unused -> new AestheticDialog.Builder((Activity) context, DialogStyle.TOASTER, DialogType.SUCCESS)
                    .setTitle("Successfully")
                    .setMessage("New account added.")
                    .setCancelable(false)
                    .setDarkMode(false)
                    .setGravity(Gravity.TOP)
                    .setAnimation(DialogAnimation.SLIDE_RIGHT)
                    .setOnClickListener(AestheticDialog.Builder::dismiss)
                    .show());
        } else {
            Warning((Activity) context);
        }

    }

    public void Warning(Activity context) {
        new AestheticDialog.Builder((Activity) context, DialogStyle.TOASTER, DialogType.WARNING)
                .setTitle("Warning")
                .setMessage("Please Enter required field.")
                .setCancelable(false)
                .setDarkMode(false)
                .setGravity(Gravity.TOP)
                .setAnimation(DialogAnimation.SLIDE_RIGHT)
                .setOnClickListener(AestheticDialog.Builder::dismiss)
                .show();
    }
}
