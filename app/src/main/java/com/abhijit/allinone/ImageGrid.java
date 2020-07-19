package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

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
        gv.setAdapter(new ImageAdapter(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ImageGrid.this, "Image Position: " + AppGlobalSetting.imagefooter[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}