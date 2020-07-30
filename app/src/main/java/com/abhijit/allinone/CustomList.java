package com.abhijit.allinone;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.FirebaseChat.FirebaseMainChatActivity;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> implements View.OnClickListener{

    private final Activity context;
    //private final String[] web;
    private final Integer[] imageId1;
    private final Integer[] imageId2;
    private final Integer[] imageId3;

    public int positionG=0;
    ArrayList<String> web = new ArrayList<>();
    String REGISTER_NUMBER="93430771993";
    TextView txtTitle;
    View growView;


    public CustomList(Activity context,
                      ArrayList<String> web, Integer[] imageId1,Integer[] imageId2,Integer[] imageId3) {
        super(context, R.layout.list_icon, web);
        this.context = context;
        this.web = web;
        this.imageId1 = imageId1;
        this.imageId2 = imageId2;
        this.imageId3=imageId3;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_icon, null, true);
        txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.img1);
        ImageView imageView2 = (ImageView) rowView.findViewById(R.id.img2);
        ImageView imageView3 = (ImageView) rowView.findViewById(R.id.img3);

        positionG=position;

        System.out.println("The List text is "+web.get(position));
        txtTitle.setText(web.get(position));

       // imageView1.setImageResource(imageId1[position]);
        //imageView2.setImageResource(imageId2[position]);
        imageView1.setTag(positionG);
        imageView2.setTag(positionG);
        imageView3.setTag(positionG);



        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);


        return rowView;
    }


    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        System.out.println("The number is "+object.toString()+"Position is "+position);
       // DataModel dataModel=(DataModel)object;

        switch (v.getId()) {
            case R.id.img1:
                System.out.println("The number  CALL is "+object.toString()+"Position is "+position);
                Uri u = Uri.parse("tel:" + object.toString());
                context.startActivity(new Intent(Intent.ACTION_DIAL,u));
                break;

            case R.id.img2:
                System.out.println("The number SMS is "+object.toString()+"Position is "+position);
                UserDetails.chatWith = object.toString();
                UserDetails.username=REGISTER_NUMBER;
                context.startActivity(new Intent(context, FirebaseMainChatActivity.class));
                break;

            case R.id.img3:
                System.out.println("The number SMS is "+object.toString()+"Position is "+position);
                UserDetails.chatWith = object.toString();
                UserDetails.username=REGISTER_NUMBER;
                AppGlobalSetting.login_category="Citizen";
                context.startActivity(new Intent(context, TaskAssignment.class));
                break;
        }


    }
}
