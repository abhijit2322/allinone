package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Display_Sms_Call extends AppCompatActivity {


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



    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId1 = {
            R.drawable.chat1,
            R.drawable.chat1,
            R.drawable.chat1,
            R.drawable.chat1,
            R.drawable.chat1,
            R.drawable.chat1,
            R.drawable.chat1

    };
    Integer[] imageId2 = {
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1

    };

    Integer[] imageId3 = {
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1,
            R.drawable.call1

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sms_call);

        //AppGlobalSetting.occopassion="Doctor";
        al=read_FirebaseData();



    }

 public ArrayList<String> read_FirebaseData()
 {

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
                             al.add(Cnumber);//Cname + "(" + Cnumber + "-" + Coccu + ")");
                             totalUsers++;
                         }
                     }

                 }
                 else
                 {
                     Log.i(TAG, "Abhijit >>Off-line user name  = " + next.child("contact_number").getValue());
                 }
             }
             if(totalUsers>0) {
                 CustomList adapter = new
                         CustomList(Display_Sms_Call.this, al, imageId1, imageId2,imageId3);
                 list = (ListView) findViewById(R.id.list);
                 list.setAdapter(adapter);
                 list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                     @Override
                     public void onItemClick(AdapterView<?> parent, View view,
                                             int position, long id) {
                         Toast.makeText(Display_Sms_Call.this, "You Clicked at " + al.get(position), Toast.LENGTH_SHORT).show();

                     }
                 });
             }

         }
         @Override
         public void onCancelled(DatabaseError databaseError) {
         }
     };



     mQuery.addListenerForSingleValueEvent(mQueryValueListener);
     Log.i(TAG, "Abhijit >> This is coming herer ----- at last"+totalUsers);

     return al;

 }


}
