package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout personalinfo, experience, review;
    TextView personalinfobtn, experiencebtn, reviewbtn,profile_name,profession_name,about_me,contact_no
            ,email_id,place_name,skill_names,company_email,company_location,exp_title,exp_place,exp_duration,
            mydesc;

    ImageView profile_Image,back,gotask,noti_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);
        String gender="m";

        personalinfo = findViewById(R.id.personalinfo);
        experience = findViewById(R.id.experience);
        review = findViewById(R.id.review);
        personalinfobtn = findViewById(R.id.personalinfobtn);
        experiencebtn = findViewById(R.id.experiencebtn);
        reviewbtn = findViewById(R.id.reviewbtn);
        back=findViewById(R.id.goback);
        gotask=findViewById(R.id.gotask);



        profile_name=findViewById(R.id.profile_name);
        profession_name=findViewById(R.id.profession_name);
        about_me=findViewById(R.id.about_me);
        mydesc=findViewById(R.id.mydesc);
        contact_no=findViewById(R.id.contact_no);
        email_id=findViewById(R.id.email_id);
        place_name=findViewById(R.id.place_name);
        skill_names=findViewById(R.id.skill_names);
        company_email=findViewById(R.id.company_email);
        company_location=findViewById(R.id.company_location);
        exp_title=findViewById(R.id.exp_title);
        exp_place=findViewById(R.id.exp_place);
        exp_duration=findViewById(R.id.exp_duration);
        profile_Image=findViewById(R.id.profile_Image);

        //back.setOnClickListener(this);


        Intent i = getIntent();

        String customer_id=i.getStringExtra("cust_id");

        profile_name.setText(i.getStringExtra("name"));
        if (i.getStringExtra("desg").isEmpty())
            profession_name.setText(i.getStringExtra("skill")+"(My Id- "+customer_id+" )");
        else
            profession_name.setText(i.getStringExtra("desg")+"(My Id-"+customer_id+")");

        about_me.setText(i.getStringExtra("about_me"));

        contact_no.setText(i.getStringExtra("contact_number"));
        email_id.setText(i.getStringExtra("email"));
        place_name.setText(i.getStringExtra("address"));
        skill_names.setText(i.getStringExtra("skill"));
        company_email.setText(i.getStringExtra("email"));
        if (i.getStringExtra("company_address").isEmpty())
            company_location.setText(i.getStringExtra("address"));
        else
            company_location.setText(i.getStringExtra("company_address"));

        exp_title.setText(i.getStringExtra("skill"));


        if (i.getStringExtra("company_address").isEmpty())
            exp_place.setText(i.getStringExtra("address"));
        else
            exp_place.setText(i.getStringExtra("company_address"));

        exp_duration.setText(i.getStringExtra("experience"));

        if(i.getStringExtra("gender").contains("M"))
            profile_Image.setImageResource(R.drawable.user_male);
        else
            profile_Image.setImageResource(R.drawable.user_female);






        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        experience.setVisibility(View.GONE);
        review.setVisibility(View.GONE);


        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                experience.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        experiencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                experience.setVisibility(View.VISIBLE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                experiencebtn.setTextColor(getResources().getColor(R.color.blue));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                experience.setVisibility(View.GONE);
                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.blue));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //actions
                if( UserDetails.came_from.equals("Display_Sms_Call"))
                {
                    startActivity(new Intent(getApplicationContext(), Display_Sms_Call.class));
                }
                else{

                 startActivity(new Intent(getApplicationContext(), RegistrationForm.class));
            }
            }

        });
        gotask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if( !UserDetails.came_from.equals("Display_Sms_Call")) {
                    startActivity(new Intent(getApplicationContext(), TaskAssignment.class));
                }
            }

        });
    }
}
