package com.abhijit.allinone;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.FirebaseChat.FirebaseMainChatActivity;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    //private final String[] web;
    private final Integer[] imageId1;
    private final Integer[] imageId2;
    public int positionG=0;
    ArrayList<String> web = new ArrayList<>();
    String REGISTER_NUMBER="93430771993";


    public CustomList(Activity context,
                      ArrayList<String> web, Integer[] imageId1,Integer[] imageId2) {
        super(context, R.layout.list_icon, web);
        this.context = context;
        this.web = web;
        this.imageId1 = imageId1;
        this.imageId2 = imageId2;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_icon, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img1);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.img2);

        positionG=position;

        System.out.println("The List text is "+web.get(position));
        txtTitle.setText(web.get(position));

       // imageView1.setImageResource(imageId1[position]);
        //imageView2.setImageResource(imageId2[position]);

        imageView2.setOnClickListener(new View.OnClickListener() {
            String s = "SMS "+web.get(positionG);;//imageId1[positionG];
            @Override
            public void onClick(View v) {

                UserDetails.chatWith = web.get(positionG);
                UserDetails.username=REGISTER_NUMBER;
                Intent i =new Intent();
                //Intent intent = new Intent(context, FirebaseMainChatActivity.class);
                Toast.makeText(context, "user selected to chat"+UserDetails.chatWith, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, FirebaseMainChatActivity.class));

                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            String s = "Call "+web.get(positionG);//items[position];

            @Override
            public void onClick(View v) {
                UserDetails.chatWith = web.get(positionG);
                Uri u = Uri.parse("tel:" + UserDetails.chatWith);

                try
                {
                    context.startActivity(new Intent(Intent.ACTION_DIAL,u));
                }
                catch (SecurityException s)
                {
                    Toast.makeText(context, s.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }

                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });


        return rowView;
    }
}
