package com.abhijit.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.abhijit.allinone.model.FirebaseDBModel_Provider;
import com.abhijit.allinone.model.Registration_Form;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.SecureRandom;
import java.util.Random;

public class RegistrationForm extends AppCompatActivity {


    EditText reg_name,reg_designation,reg_address,reg_contact_number,reg_email_address,reg_com_address,
            reg_exp,reg_company_address,reg_about_me;
    Button register;
    Spinner gender_spinner,skill_spinner;

    String ran_cust;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);



        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("Person_data");

        reg_name=findViewById(R.id.reg_name);
        reg_designation=findViewById(R.id.reg_designation);
        reg_address=findViewById(R.id.reg_address);
        reg_contact_number=findViewById(R.id.reg_contact_number);
        reg_email_address=findViewById(R.id.reg_email_address);
        reg_com_address=findViewById(R.id.reg_com_address);
        reg_exp=findViewById(R.id.reg_exp);
        reg_company_address=findViewById(R.id.reg_company_address);
        reg_about_me=findViewById(R.id.reg_about_me);
        register=findViewById(R.id.register);
        gender_spinner=findViewById(R.id.gender_spinner);
        skill_spinner=findViewById(R.id.skill_spinner);


    }

    public void sendRegistration(View button) {

        String name = reg_name.getText().toString();
        String desg = reg_designation.getText().toString();
        String address = reg_address.getText().toString();
        String contact_number = reg_contact_number.getText().toString();
        String email = reg_email_address.getText().toString();
        String com_address = reg_com_address.getText().toString();
        String experience = reg_exp.getText().toString();
        String company_address = reg_company_address.getText().toString();
        String about_me = reg_about_me.getText().toString();
        String gender=gender_spinner.getSelectedItem().toString();
        String skill=skill_spinner.getSelectedItem().toString();

        userId=contact_number;

        if(name.isEmpty()||address.isEmpty()||contact_number.isEmpty()||gender.isEmpty()||skill.isEmpty())
        {
            Toast.makeText(RegistrationForm.this, "Enter missing information", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SecureRandom random = new SecureRandom();
            int num = random.nextInt(100000);
            ran_cust = String.format("%05d", num);

            Intent i = new Intent(RegistrationForm.this, ProfileActivity.class);
            i.putExtra("name",name);
            i.putExtra("desg",desg);
            i.putExtra("address",address);
            i.putExtra("contact_number",contact_number);
            i.putExtra("email",email);
            i.putExtra("com_address",com_address);
            i.putExtra("experience",experience);
            i.putExtra("company_address",company_address);
            i.putExtra("about_me",about_me);
            i.putExtra("gender",gender);
            i.putExtra("skill",skill);
            i.putExtra("cust_id",ran_cust);
            Save_RegisterData();
            startActivity(i);
        }


    }


    private void Save_RegisterData() {

        Registration_Form pbdata=new Registration_Form();
        pbdata.setName(reg_name.getText().toString());
        pbdata.setAddress(reg_address.getText().toString());
        pbdata.setContact_number(reg_contact_number.getText().toString());
        pbdata.setAbout_me(reg_about_me.getText().toString());
        pbdata.setCom_address( reg_com_address.getText().toString());
        pbdata.setCompany_address(reg_company_address.getText().toString());
        pbdata.setDesg(reg_designation.getText().toString());
        pbdata.setEmail(reg_email_address.getText().toString());
        pbdata.setExperience(reg_exp.getText().toString());
        pbdata.setRan_cust(ran_cust);
        pbdata.setGender(gender_spinner.getSelectedItem().toString());
        pbdata.setSkill(skill_spinner.getSelectedItem().toString());

        mDbRef.child(userId).setValue(pbdata);

        mDbRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //FirebaseDBModel_Provider user = dataSnapshot.getValue(FirebaseDBModel_Provider.class);
                Toast.makeText(RegistrationForm.this, "User Registered",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Toast.makeText(RegistrationForm.this, "User Registered Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
