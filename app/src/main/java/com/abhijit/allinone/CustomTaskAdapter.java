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

public class CustomTaskAdapter extends ArrayAdapter<String> {
    private static final String TAG = "CustomTaskAdapter";
    private final Activity context;
    //private final String[] web;
    private final Integer[] imageId1;
    private final Integer[] imageId2;
    public int positionG=0;
    ArrayList<String> web = new ArrayList<>();
    String REGISTER_NUMBER="93430771993";
    String LOGIN_SERVICE_MAN="83430771990";

    ArrayList<String> taskList = new ArrayList<>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    private String userId;
    private String selectedTaskName;
    private String taskNameUpdate;
    private String updateValue;

    public void Callsupper()
    {

    }
    public CustomTaskAdapter(Activity context,
                      ArrayList<String> web, Integer[] imageId1,Integer[] imageId2) {


            super(context, R.layout.task_custom_adapter, web);
            this.context = context;
        this.web = web;
            this.imageId1 = imageId1;
            this.imageId2 = imageId2;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.task_custom_adapter, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img1);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.img2);

        positionG=position;

       // System.out.println("The List text is "+web.get(position));
        txtTitle.setText(web.get(position));

        // imageView1.setImageResource(imageId1[position]);
        //imageView2.setImageResource(imageId2[position]);

        imageView1.setOnClickListener(new View.OnClickListener() {
            String s = web.get(positionG);;//imageId1[positionG];
            @Override
            public void onClick(View v) {

                UserDetails.chatWith = web.get(positionG);
                UserDetails.username=REGISTER_NUMBER;
                //Intent i =new Intent();
                //Intent intent = new Intent(context, FirebaseMainChatActivity.class);
                Toast.makeText(context, "select "+s ,Toast.LENGTH_SHORT).show();
                selectedTaskName=s;//web.get(positionG);
                Update_Task("true",selectedTaskName);
                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

                context.startActivity(new Intent(context, TaskAssignment.class));
                context.finish();
                //Intent intent = getIntent();//TaskAssignment

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            String s = web.get(positionG);//items[position];

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"select"+s, Toast.LENGTH_SHORT).show();
                selectedTaskName=s;//web.get(positionG);
                Update_Task("false",selectedTaskName);

                context.startActivity(new Intent(context, TaskComplete.class));
                context.finish();
            }
        });


        return rowView;
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
