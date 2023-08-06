package com.sharebysocial.com.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private final DatabaseReference databaseReference;

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getLocationReference(String userId){
        return databaseReference.child(userId).child("location").child("0");
    }
}
