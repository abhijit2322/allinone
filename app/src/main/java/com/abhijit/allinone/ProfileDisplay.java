package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class ProfileDisplay extends AppCompatActivity {

    private static final String TAG = "ProfileDisplay";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display); //Registration_Form
        String serviceman="9740856007";
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("Person_data");

        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Log.i(TAG, "Abhijit >>>>>>>>>>>>>>>>>>>>>>>>>>Value = " + next.child("skill").getValue());
                    Log.i(TAG, "Abhijit >>>>>>>>>>>>>>>>>>>>>>>>>>Value = " + next.child("contact_number").getValue());
                    Log.i(TAG, "Abhijit >>>>>>>>>>>>>>>>>>>>>>>>>>Value = " + next.child("gender").getValue());
                    Log.i(TAG, "Abhijit >>>>>>>>>>>>>>>>>>>>>>>>>>Value = " + next.child("name").getValue());

                    Intent i = new Intent(ProfileDisplay.this, ProfileActivity.class);
                    i.putExtra("name", next.child("name").getValue().toString());
                    i.putExtra("desg",next.child("desg").getValue().toString());
                    i.putExtra("address",next.child("address").getValue().toString());
                    i.putExtra("contact_number",next.child("contact_number").getValue().toString());
                    i.putExtra("email",next.child("email").getValue().toString());
                    i.putExtra("com_address",next.child("company_address").getValue().toString());
                    i.putExtra("experience",next.child("experience").getValue().toString());
                    i.putExtra("company_address",next.child("company_address").getValue().toString());
                    i.putExtra("about_me",next.child("about_me").getValue().toString());
                    i.putExtra("gender",next.child("gender").getValue().toString());
                    i.putExtra("skill",next.child("skill").getValue().toString());
                    i.putExtra("cust_id",next.child("ran_cust").getValue().toString());
                    i.putExtra("charges",next.child("charge").getValue().toString());
                    startActivity(i);


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        mQuery.addListenerForSingleValueEvent(mQueryValueListener);
    }
}