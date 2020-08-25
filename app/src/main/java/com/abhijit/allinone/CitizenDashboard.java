package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CitizenDashboard extends AppCompatActivity {

    ImageButton imb_task;
    TextView tv1,tv2;
    ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_dashboard);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        imv=(ImageView) findViewById(R.id.imv1);
        imb_task = (ImageButton) findViewById(R.id.go_right);


        imb_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(CitizenDashboard.this, ImageGrid.class));
            }

        });

    }


}
