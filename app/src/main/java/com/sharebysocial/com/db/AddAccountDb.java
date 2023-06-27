package com.sharebysocial.com.db;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharebysocial.com.Model.AccountModel;

import java.util.Objects;

public class AddAccountDb {
    DatabaseReference database;
    AccountModel model;

    public AddAccountDb(String accountName, String userName, Context context) {
        if (!Objects.equals(accountName, "Select Profile")) {


            model = new AccountModel(accountName, userName);
            database = FirebaseDatabase.getInstance().getReference().child(accountName);
            database.setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "Account is Added", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "Please Select Account", Toast.LENGTH_SHORT).show();
        }

    }
}
