package com.abhijit.allinone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoard extends AppCompatActivity {
    TextView edutv,enttv,careertv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        edutv = (TextView) findViewById(R.id.edu);
        edutv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at " +edutv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
               // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);

               // startActivity(ii);

            }
        });
        findViewById(R.id.educard).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at card " +edutv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
                // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);
                Intent intent = new Intent(DashBoard.this,MainActivity.class);
                intent.putExtra("content_type", "education");
                startActivity(intent);

                // startActivity(ii);

            }
        });

        enttv = (TextView) findViewById(R.id.ent);
        enttv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at " +enttv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
                // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);

                // startActivity(ii);

            }
        });
        findViewById(R.id.entcard).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at card " +enttv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
                // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);
                Intent intent = new Intent(DashBoard.this,MainActivity.class);
                intent.putExtra("content_type", "entertainment");
                startActivity(intent);
                // startActivity(ii);

            }
        });

        careertv = (TextView) findViewById(R.id.career);
        careertv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at " +careertv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
                // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);

                // startActivity(ii);

            }
        });
        findViewById(R.id.careercard).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(DashBoard.this, "You Clicked at card " +careertv.getText().toString(), Toast.LENGTH_SHORT).show();
                //Intent ii = new Intent();
                // ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);
                Intent intent = new Intent(DashBoard.this,MainActivity.class);
                intent.putExtra("content_type", "career");
                startActivity(intent);
                // startActivity(ii);

            }
        });



    }
}
