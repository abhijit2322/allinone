package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.FirebaseChat.FirebaseMainChatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class OnlinePresentUser extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;

    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    int la;
    String REGISTER_NUMBER="93430771993";

    //ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_present_user);

        usersList = (ListView)findViewById(R.id.usersList);
        noUsersText = (TextView)findViewById(R.id.noUsersText);

        la=android.R.layout.simple_list_item_1;


       // pd = new ProgressDialog(OnlinePresentUser.this);
       // pd.setMessage("Loading...");
       // pd.show();


        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/PhoneNumber");

        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i(TAG, "Abhijit >> This is coming herer ----- coming here" );

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Log.i(TAG, "Abhijit >>Value = " + next.child("isonline").getValue());
                    boolean  online_status=(boolean)next.child("isonline").getValue();
                    System.out.println("the value"+online_status);
                    if(online_status){
                        Log.i(TAG, "Abhijit >>On-line user name  = " + next.child("contact_number").getValue()+"   "+AppGlobalSetting.occopassion);
                        String Cname= (String)next.child("name").getValue();
                        String Cnumber= (String)next.child("contact_number").getValue();
                        String Coccu= (String)next.child("occupassion").getValue();

                        if(Coccu.trim().contains(AppGlobalSetting.occopassion))
                            Log.i(TAG, "Abhijit >>On-line user name  if occupassion  = "+Coccu.trim());
                        else
                            Log.i(TAG, "Abhijit >>On-line user name else occupassion  = "+Coccu.trim());

                        if(Coccu.trim().contains(AppGlobalSetting.occopassion)) {
                            Log.i(TAG, "Abhijit >>On-line user  inisde name  occupassion  = "+Coccu.trim());
                            if (!Cnumber.contains(REGISTER_NUMBER)) {
                                al.add(Cname + "(" + Cnumber + "-" + Coccu + ")");
                                totalUsers++;
                            }
                        }

                    }
                    else
                    {
                        Log.i(TAG, "Abhijit >>Off-line user name  = " + next.child("contact_number").getValue());
                    }
                }


                Log.i(TAG, "Abhijit >>Off-line user name  = " +totalUsers);
                if(totalUsers <1){
                    noUsersText.setVisibility(View.VISIBLE);
                    usersList.setVisibility(View.GONE);
                }
                else{
                    noUsersText.setVisibility(View.GONE);
                    usersList.setVisibility(View.VISIBLE);
                    usersList.setAdapter(new ArrayAdapter<String>(getApplicationContext(),la, al));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };



/*
        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this,la, al));
        }
        */





        //pd.dismiss();

        mQuery.addListenerForSingleValueEvent(mQueryValueListener);
        Log.i(TAG, "Abhijit >> This is coming herer ----- at last"+totalUsers);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                UserDetails.username=REGISTER_NUMBER;
                Toast.makeText(OnlinePresentUser.this, "user selected to chat"+UserDetails.chatWith, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OnlinePresentUser.this, FirebaseMainChatActivity.class));
            }
        });


    }


}
