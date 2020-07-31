package com.abhijit.allinone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.abhijit.allinone.FirebaseChat.FirebaseMainChatActivity;
import com.abhijit.allinone.model.FirebaseDBModel_Provider;
import com.abhijit.allinone.model.TodoTask;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class TaskAssignment extends AppCompatActivity {
    private static final String TAG = "TaskAssignment";
   // private TaskDbHelper mHelper;
    private ListView mTaskListView;

    private ListView MTaskListView;
    TextView mTaskText;
    TextView listtextview;
    private ArrayAdapter<String> mAdapter;
    String REGISTER_NUMBER=UserDetails.username;
    String LOGIN_SERVICE_MAN=UserDetails.chatWith;
    String citizenRequest="";
    String sub_folder;
    ArrayList<String> taskList = new ArrayList<>();

    CustomTaskAdapter ser_adapter;
    CitizenTaskAdapter ci_adapter;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    private String userId;
    int task_adapter;
    int task_number=0;


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
            R.drawable.accept,
            R.drawable.accept,
            R.drawable.accept,
            R.drawable.accept,
            R.drawable.accept,
            R.drawable.accept,
            R.drawable.accept

    };
    Integer[] imageId2 = {
            R.drawable.task_complete,
            R.drawable.task_complete,
            R.drawable.task_complete,
            R.drawable.task_complete,
            R.drawable.task_complete,
            R.drawable.task_complete,
            R.drawable.task_complete

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assignment);

       // mHelper = new TaskDbHelper(this);
       // mTaskListView = (ListView) findViewById(R.id.list_todo);


       // task_adapter=android.R.layout.simple_list_item_1;

        //task_adapter=R.layout.item_todo;

        taskList = new ArrayList<>();



        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/TodoList");
        Log.d(TAG, "Read task called in on create...............");
        Read_Task();
        //updateUI();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(UserDetails.userType.contains("Citizen")){
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                citizenRequest = String.valueOf(taskEditText.getText());
                                //taskList.add(citizenRequest);
                                if(!citizenRequest.isEmpty()) {
                                    Save_Task(citizenRequest);

                                }
                                else
                                {
                                    return;
                                }

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void rejectTask(View view) {

        Log.d(TAG, "Rejected Task pressed...............");
       // Update_Task("false");
        updateUI();

    }
    public void acceptTask(View view) {
        //View av=mAdapter.getView(R.id.task_title,null,null);
        //mAdapter.getPosition(av.getcl)


        Log.d(TAG, "Accept Task pressed...............");
        updateUI();
      //  Update_Task("true");
    }

    private void updateUI() {
/*

       // Log.d(TAG, " Total Tasks are >>>> "+taskList);

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.task_title,
                    taskList);
            listtextview=(TextView)findViewById(R.id.task_title);
            mTaskListView.setAdapter(mAdapter);

        }
        else {
           // mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        if(listtextview!=null) {
            String textv = listtextview.getText().toString();
            Log.d(TAG, " text selected :--------------- "+textv);
        }
*/

    }

    public void Save_Task(String task_details){

        String userId = UserDetails.username;
        sub_folder= new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todaysdate = dateFormat.format(date);
        System.out.println("Today's date : " + todaysdate);
        TodoTask user = new TodoTask(task_details,UserDetails.username,UserDetails.chatWith,UserDetails.charges,todaysdate,"  ",false,"No","no ","started",UserDetails.cit_lang,UserDetails.cit_lati,UserDetails.ser_lang,UserDetails.ser_lati);

        mDbRef.child(userId+"/"+sub_folder).setValue(user);

        mDbRef.child(userId+"/"+sub_folder).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                TodoTask user = dataSnapshot.getValue(TodoTask.class);

                Log.d(TAG, " Save_Task User name>>>> : " + user.getTask_name() + ", contactnumber  " + user.getAccepted_by() +"On Line Status:"+user.isAccept_reject());
            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //MTaskListView.setVisibility(View.VISIBLE);
       // MTaskListView.invalidate();

        //MTaskListView.getAdapter().notifyAll();//notifyDataSetChanged();

        taskList.clear();
        Log.d(TAG, "Read task called in on save task..............");
        Read_Task();

    }

    public void Read_Task(){

        String userId = LOGIN_SERVICE_MAN;
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/TodoList");

        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(!taskList.isEmpty())
                {
                    Log.i(TAG, "Abhijit >> This is coming here ----- coming here" );
                    taskList.clear();
                }

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Iterable<DataSnapshot> sub_snapshotIterator = next.getChildren();
                    Iterator<DataSnapshot> sub_iterator = sub_snapshotIterator.iterator();
                    while (sub_iterator.hasNext()){
                        DataSnapshot final_next = (DataSnapshot) sub_iterator.next();
                       // Log.i(TAG, "Abhijit >> sub Task value Read Task = "+final_next.child("task_name").getValue());
                        String Taskname= (String)final_next.child("task_name").getValue();
                        if(Taskname!=null) {
                            boolean accept_reject = (boolean) final_next.child("accept_reject").getValue();
                            String accepted_by = (String) final_next.child("accepted_by").getValue();
                            String asignee_number=(String)final_next.child("assigned_by").getValue();
                            String cit_lang=(String)final_next.child("cit_lang").getValue();
                            String cit_lati=(String)final_next.child("cit_lati").getValue();
                            Log.i(TAG, "Abhijit >> sub Task value = "+accept_reject +"   taskname "+Taskname+" Accepted by "+accepted_by+" Accepted by "+UserDetails.username);
                            if (accepted_by.contains(UserDetails.username)) {
                                Log.i(TAG, "Abhijit >> Accepted by................");
                                UserDetails.cit_lati=cit_lati;
                                UserDetails.cit_lang=cit_lang;
                                taskList.add(Taskname);
                                task_number++;
                            }
                            if ((asignee_number.contains(UserDetails.username)))
                            {
                                Log.i(TAG, "Abhijit >> Assignee by...............");
                                taskList.add(Taskname);
                                task_number++;
                            }
                        }
                    }
              }
                //Log.d(TAG, " Total Tasks are >>>>55555555 "+taskList);
                if(task_number >0){

                    if(UserDetails.userType.equals("Citizen")){
                         ci_adapter = new
                                CitizenTaskAdapter(TaskAssignment.this, taskList);

                        MTaskListView = (ListView) findViewById(R.id.list_todo);
                        MTaskListView.setAdapter(ci_adapter);
                       /* MTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Toast.makeText(TaskAssignment.this, "You Clicked at " +  position, Toast.LENGTH_SHORT).show();

                            }
                        });*/
                    }
                    else {
                        if(ser_adapter==null) {

                            Log.i(TAG, "Abhijit >> This is Comming Adapter is null" );
                            ser_adapter = new
                                    CustomTaskAdapter(TaskAssignment.this, taskList, imageId1, imageId2);

                            ser_adapter.setNotifyOnChange(true);
                            MTaskListView = (ListView) findViewById(R.id.list_todo);
                            MTaskListView.setAdapter(ser_adapter);
                            //adapter.notifyDataSetChanged();
                            MTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(TaskAssignment.this, "You Clicked at " + position, Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                        else
                            {
                                Log.i(TAG, "Abhijit >> This is Comming Adapter is Not null" );
                                ser_adapter.addAll(taskList);
                                ser_adapter.notifyDataSetChanged();
                            }
                    }

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };



        mQuery.addListenerForSingleValueEvent(mQueryValueListener);




}

    public void Update_Task(String updatevalue){

        String userId = LOGIN_SERVICE_MAN;


        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("ServiceProvider/TodoList");

        Query mQuery = mDbRef.orderByKey();

        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i(TAG, "Abhijit >> This is coming herer ----- coming here" );

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    Iterable<DataSnapshot> sub_snapshotIterator = next.getChildren();
                    Iterator<DataSnapshot> sub_iterator = sub_snapshotIterator.iterator();
                    while (sub_iterator.hasNext()){
                        DataSnapshot final_next = (DataSnapshot) sub_iterator.next();
                        Log.i(TAG, "Abhijit >> sub Task value = ");//+final_next.child("task_name").getValue());
                       // String Taskname= (String)final_next.child("task_name").getValue();

                       // boolean accept_reject= (boolean)final_next.child("accept_reject").getValue();
                        //Log.i(TAG, "Abhijit >> sub Task value = "+Taskname);
                       // if(accept_reject==false)
                         //   taskList.add(Taskname);
                    }
                }
               // Log.d(TAG, " Total Tasks are >>>>55555555 "+taskList);
                updateUI();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(TaskAssignment.this,"Result ok.before.......",
                    Toast.LENGTH_SHORT).show();
            if (resultCode  == RESULT_OK) {

                Toast.makeText(TaskAssignment.this,"Result ok........",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(TaskAssignment.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onStart() {
     super.onStart();
        Log.i(TAG, "Abhijit >> ON START--------- coming here" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Abhijit >> ON RESUME--------- coming here" );
       // Log.d(TAG, "Read task called in on RESUME...............");
       // Read_Task();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Abhijit >> ON PAUSE--------- coming here" );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Abhijit >> ON RE-START--------- coming here" );
    }
    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "Abhijit >> ON STOP--------- coming here" );
    }
}