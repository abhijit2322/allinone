package com.abhijit.allinone;

import androidx.appcompat.app.AppCompatActivity;

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

    ImageView profile_Image;

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


        profile_name.setText("Abhijit Sarkar");
        profession_name.setText("Senior Architect "+"My iD:"+"124321");
        about_me.setText("I am well good problem solver");
        contact_no.setText("+919740856007");
        email_id.setText("abc@gmail.com");
        place_name.setText("Agartala");
        skill_names.setText("plumbing,painting");
        company_email.setText("DHDGD@gmail.com");
        company_location.setText("Jirania-Tripura");
        exp_title.setText("Plumber");
        exp_place.setText("Agaratala");
        exp_duration.setText("1998 to 2022");

        if(gender.contains("m"))
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
    }
}
