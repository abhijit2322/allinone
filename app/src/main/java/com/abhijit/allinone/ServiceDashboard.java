package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceDashboard extends AppCompatActivity {

    ImageButton imb_task;
    TextView tv1,tv2;
    ImageView imv;
    String register_status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard);

        Intent i = getIntent();
        register_status=i.getStringExtra("registered");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        imv=(ImageView) findViewById(R.id.imv1);
        imb_task = (ImageButton) findViewById(R.id.go_right);


        imb_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(register_status.contains("yes"))
                   startActivity(new Intent(ServiceDashboard.this, TaskAssignment.class));
                else
                    startActivity(new Intent(ServiceDashboard.this, RegistrationForm.class));
            }

        });
    }
}
