package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ProcessCitizenRequest extends AppCompatActivity {

    String[] imagefooter;
    boolean pass_for_single=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_citizen_request);
        Intent i = getIntent();
        String request_for=i.getStringExtra("request_for");


        if(request_for.contains("house"))
            imagefooter=AppGlobalSetting.imagefooter_house;
        else if(request_for.contains("education"))
            imagefooter=AppGlobalSetting.imagefooter_education;
        else if(request_for.contains("medical"))
            imagefooter=AppGlobalSetting.imagefooter_medical;
        else if(request_for.contains("construction"))
            imagefooter=AppGlobalSetting.imagefooter_construction;
        else if(request_for.contains("entertainment"))
            imagefooter=AppGlobalSetting.imagefooter_entertainment;
        else
            pass_for_single=true;



        if(pass_for_single ==false) {
        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this,request_for));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    Toast.makeText(ProcessCitizenRequest.this, "Image Position: " + imagefooter[position], Toast.LENGTH_SHORT).show();


            }
        });
        }
        else
            pass_for_single=false;

    }
}
