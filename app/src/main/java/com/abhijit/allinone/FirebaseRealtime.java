package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.abhijit.allinone.model.FirebaseDBModel_Provider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class FirebaseRealtime extends AppCompatActivity {
    private static final String TAG = "FirebaseRealtime";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_realtime);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/PhoneNumber");

//Setting firebase unique key for Hashmap list
        //String userId = "93430771995";//mDbRef.push().getKey();
        //String userId = mDbRef.push().getKey();
// creating user object
        String userId = "83430771990";
        FirebaseDBModel_Provider user = new FirebaseDBModel_Provider("Hillary5","83430771990","Agaratla","Painter",false);

        mDbRef.child(userId).setValue(user);

        mDbRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseDBModel_Provider user = dataSnapshot.getValue(FirebaseDBModel_Provider.class);

                Log.d(TAG, "User name>>>> : " + user.getName() + ", contactnumber  " + user.getContact_number() +"On Line Status:"+user.isIsonline());
            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //update db
       // String name = "sanjaudutt....";
       // mDbRef.child(userId).child("name").setValue(name);
/*
        for (DataSnapshot child : mDataSnapshot.getChildren()) {
            Log.i(TAG, child.getKey());
            Log.i(TAG, child.getValue(String.class));
        }
*/
        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Log.i(TAG, "Abhijit >>Value = " + next.child("name").getValue());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        mQuery.addListenerForSingleValueEvent(mQueryValueListener);
    }
}
