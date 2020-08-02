package com.abhijit.allinone.trackdelivary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abhijit.allinone.R;
import com.abhijit.allinone.UserDetails;
import com.abhijit.allinone.trackdelivary.util.Constants;

public class MainTrackerDelivary extends AppCompatActivity {

    Button userButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tracker_delivary);
        userButton = (Button) findViewById(R.id.user_login_button);
       /* userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTrackerDelivary.this, LoginActivity.class);
                intent.putExtra(Constants.LOGINTYPE, "U");
                startActivity(intent);
            }
        });*/
        String loging_type= UserDetails.userType;
        String user_name=UserDetails.username;
        if(loging_type.equals("Service Provider")) {

                Intent intent = new Intent(this, DboyActivity.class);
                intent.putExtra(Constants.CURRENT_DELBOY, user_name);
                startActivity(intent);
                finish();

        } else {
           // String userId = "kalyan123";
           // Intent intent = new Intent(this, ShowUserOrdersActivity.class);
           // intent.putExtra(Constants.CURRENT_USER, user_name);
           // startActivity(intent);
            Intent intent = new Intent(this, OrderDetailActivity.class);
            //intent.putExtra(Constants.ORDER_ID, orders[i]);
            intent.putExtra(Constants.MAPS_TYPE, "U");
            startActivity(intent);
        }

    }

   /* public void deliveryBoy(View v) {
        Intent intent = new Intent(MainTrackerDelivary.this, LoginActivity.class);
        intent.putExtra(Constants.LOGINTYPE, "D");
        startActivity(intent);
    }*/
}
