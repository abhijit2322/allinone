package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tutlane on 24-08-2017.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String citizen_request;
    Integer[] thumbImages;
    String[] imagefooter;

    public ImageAdapter(Context c,String Cr) {
        mContext = c;
        citizen_request=Cr;
        if(citizen_request.contains("main")){
            thumbImages=AppGlobalSetting.thumbImages_main;
            imagefooter=AppGlobalSetting.imagefooter_main;
        }
        else if(citizen_request.contains("house")){
            thumbImages=AppGlobalSetting.thumbImages_house;
            imagefooter=AppGlobalSetting.imagefooter_house;
        }
        else if(citizen_request.contains("education")){
            thumbImages=AppGlobalSetting.thumbImages_education;
            imagefooter=AppGlobalSetting.imagefooter_education;
        }
        else if(citizen_request.contains("medical")){
            thumbImages=AppGlobalSetting.thumbImages_medical;
            imagefooter=AppGlobalSetting.imagefooter_medical;
        }
        else if(citizen_request.contains("construction")){
            thumbImages=AppGlobalSetting.thumbImages_construction;
            imagefooter=AppGlobalSetting.imagefooter_construction;
        }
        else if(citizen_request.contains("entertainment")){
            thumbImages=AppGlobalSetting.thumbImages_entertainment;
            imagefooter=AppGlobalSetting.imagefooter_entertainment;
        }

    }
    public int getCount() {
        return thumbImages.length;
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
       /* ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);
        imageView.setImageResource(thumbImages[position]);
        imageView.set
        return imageView;*/



        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(imagefooter[position]);//"addwd");//web[position]);
            textView.setMarqueeRepeatLimit(5);
            imageView.setImageResource(thumbImages[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

}