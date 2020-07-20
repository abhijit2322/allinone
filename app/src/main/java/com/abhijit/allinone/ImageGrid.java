package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class ImageGrid extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);
        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this,"main"));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ImageGrid.this, "Image Position: " + AppGlobalSetting.imagefooter_main[position], Toast.LENGTH_SHORT).show();
                String request_type="others";
                if(AppGlobalSetting.imagefooter_main[position].contains("House"))
                    request_type="house";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Education"))
                    request_type="education";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Medical"))
                    request_type="medical";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Construction"))
                    request_type="construction";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Entertainment"))
                    request_type="entertainment";

                else if(AppGlobalSetting.imagefooter_main[position].contains("Grocery"))
                    request_type="grocery";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Germent"))
                    request_type="germent";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Vegitable"))
                    request_type="vegitable";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Meet&Fish"))
                    request_type="meet_fish";


               /* else if(AppGlobalSetting.imagefooter_main[position].contains("Education"))
                    request_type="education";
                else if(AppGlobalSetting.imagefooter_main[position].contains("Education"))
                    request_type="education";*/

                Intent i = new Intent(ImageGrid.this, ProcessCitizenRequest.class);
                i.putExtra("request_for",request_type);
                startActivity(i);
            }
        });
    }
}