package com.abhijit.allinone;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.trackdelivary.MainTrackerDelivary;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.FirebaseChat.FirebaseMainChatActivity;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import static android.app.Activity.RESULT_OK;

public class CitizenTaskAdapter extends ArrayAdapter<String> implements View.OnClickListener {
    private static final String TAG = "CitizenTaskAdapter";
    private final Activity context;
    //private final String[] web;

    public int positionG=0;
    ArrayList<String> web = new ArrayList<>();
  /*  String REGISTER_NUMBER="93430771993";
    String LOGIN_SERVICE_MAN="83430771990";*/

    String REGISTER_NUMBER=UserDetails.username;
    String LOGIN_SERVICE_MAN=UserDetails.username;

   // private final Integer[] imageId;

    ArrayList<String> taskList = new ArrayList<>();
    ArrayList<Integer> imageid = new ArrayList<>();
    ArrayList<Boolean> task_status = new ArrayList<>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    TextView txtTitle;

    private String userId;
    private String selectedTaskName;
    private String taskNameUpdate;
    private String updateValue;

    public void Callsupper()
    {

    }
    public CitizenTaskAdapter(Activity context,
                             ArrayList<String> web,ArrayList<Integer> imageId,ArrayList<Boolean> taskstatues) {


        super(context, R.layout.task_citizen_adapter, web);
        this.context = context;
        this.web = web;
        this.imageid=imageId;
        this.task_status=taskstatues;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.task_citizen_adapter, null, true);
        txtTitle= (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img1);
        positionG=position;
        txtTitle.setText(web.get(position));
        imageView1.setImageResource(imageid.get(position));
        task_status.set(position,task_status.get(position));
        imageView1.setTag(positionG);
//ProfileDisplay
        imageView1.setOnClickListener(this);
        return rowView;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        System.out.println("The number is >>>>>>>>>>>>>>>position>>>>>"+object.toString()+"Position is "+position);
        System.out.println("The number status is >>>>>>>>>>>value>>>>>"+object.toString()+"Position is "+task_status.get(position));
        // DataModel dataModel=(DataModel)object;

        switch (v.getId()) {
            case R.id.img1:
                System.out.println("The number  Accept is >>>>>>>>>>>>>>>>>>>>"+object.toString()+" Position is "+position);
                UserDetails.chatWith = web.get(positionG);
                UserDetails.username=REGISTER_NUMBER;
                selectedTaskName=object.toString();//web.get(positionG);
                if(task_status.get(position))
                {
                    UserDetails.chatWith = web.get(positionG);
                    UserDetails.username=REGISTER_NUMBER;
                    context.startActivity(new Intent(context, MainTrackerDelivary.class));//TaskAssignment.class));
                    context.finish();
                }
               // Update_Task("started",selectedTaskName);
              /*  try {
                    Thread.sleep(2000); //1000 milliseconds is one second.
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }*/
               // context.startActivity(new Intent(context, MainTrackerDelivary.class));//TaskAssignment.class));
               // context.finish();
                break;

            case R.id.img0:
                /*context.startActivity(new Intent(context, ProfileDisplay.class));//TaskAssignment.class));
                context.finish();*/
                break;

        }


    }


    public void Update_Task(String updatevalue,String taskName){

        String userId = LOGIN_SERVICE_MAN;
        taskNameUpdate=taskName;
        updateValue=updatevalue;

        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/TodoList");

        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    //String Taskname= (String)next.child("task_name").getValue();
                    Iterable<DataSnapshot> sub_snapshotIterator = next.getChildren();
                    Iterator<DataSnapshot> sub_iterator = sub_snapshotIterator.iterator();
                    while (sub_iterator.hasNext()){
                        DataSnapshot final_next = (DataSnapshot) sub_iterator.next();
                        //Log.i(TAG, "Abhijit >> sub Task value = "+final_next.child("task_name").getValue());
                        String Taskname= (String)final_next.child("task_name").getValue();
                        Log.i(TAG, "Abhijit >> sub Task value  present  first "+Taskname);
                        if(taskNameUpdate.equals(Taskname))
                        {
                            Log.i(TAG, "Abhijit >> sub Task value  present  "+Taskname+"  selected task:  "+taskNameUpdate+"  "+final_next.toString());
                            mDbRef=final_next.getRef();
                            if(updateValue.equals("true")) {
                                mDbRef.child("accept_reject").setValue(true);
                                // mDbRef.child(final_next).child()
                            }
                            if(updateValue.equals("false")) {
                                mDbRef.child("accept_reject").setValue(false);
                                // mDbRef.child(final_next).child()
                            }
                        }

                        // boolean accept_reject= (boolean)final_next.child("accept_reject").getValue();
                        //Log.i(TAG, "Abhijit >> sub Task value = "+Taskname);
                        // if(accept_reject==false)
                        //   taskList.add(Taskname);
                    }
                }
                // Log.d(TAG, " Total Tasks are >>>>55555555 "+taskList);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };


        mQuery.addListenerForSingleValueEvent(mQueryValueListener);



        //FirebaseDBModel_Provider user = new FirebaseDBModel_Provider("Hillary5","83430771990","Agaratla","Painter",false,"200");

        // mDbRef.child(userId).setValue(user);
       /* String name = "sanjaudutt....";
        mDbRef.child(userId).child("accepted_by").setValue(name);
        mDbRef.child(userId).child("charges").setValue(name);
        mDbRef.child(userId).child("start_date").setValue(name);
        mDbRef.child(userId).child("end_date").setValue(name);*/
        // mDbRef.child(userId).child("accept_reject").setValue(updatevalue);


    }
}
