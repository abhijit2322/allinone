package com.abhijit.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.abhijit.allinone.model.FirebaseDBModel_Provider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class Login_FireBase extends AppCompatActivity implements View.OnClickListener {

    EditText etPhone, etOtp;
    //Button btSendOtp, btResendOtp, btVerifyOtp;
    ImageButton btSendOtp, btResendOtp, btVerifyOtp;
    Spinner occpas_spinner;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    String occopastion;
    SharedPreferences sharedpreferences;
    String cit_lang="";
    String cit_lati="";
    String ser_lang="";
    String ser_lati="";
    String com_lang="";
    String com_lati="";

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);

        sharedpreferences = getSharedPreferences(UserDetails.MyPREFERENCES, Context.MODE_PRIVATE);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        initFields();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();//
        initFireBaseCallbacks();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        System.out.println("GPS provider not found");
        }


            fetchLocation();
        getLocation();
    }

    void initFields() {
        etPhone = findViewById(R.id.et_phone);
        etOtp = findViewById(R.id.et_otp);
        btSendOtp = findViewById(R.id.bt_send_otp);
       // btResendOtp = findViewById(R.id.bt_resend_otp);
       // btVerifyOtp = findViewById(R.id.bt_verify_otp);
        btResendOtp = findViewById(R.id.resend);
        btVerifyOtp = findViewById(R.id.verified);

        occpas_spinner=findViewById(R.id.occopassion);
        btResendOtp.setOnClickListener(this);
        btVerifyOtp.setOnClickListener(this);
        btSendOtp.setOnClickListener(this);
    }
    void initFireBaseCallbacks() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                System.out.println("Abhijit Verification Complete.."+credential.getSignInMethod());
                Toast.makeText(Login_FireBase.this, "Verification Complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                System.out.println("Abhijit Verification failed message.."+e.getMessage());
                Toast.makeText(Login_FireBase.this, "Verification Failed", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                System.out.println("Abhijit verification failed token.."+token.toString());
                Toast.makeText(Login_FireBase.this, "Code Sent", Toast.LENGTH_SHORT).show();
                mVerificationId = verificationId;
            }
        };
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send_otp:
                occopastion=occpas_spinner.getSelectedItem().toString();
                System.out.println("Abhijit The phone Number...."+etPhone.getText().toString()+"   "+occopastion);
                String Phonenumber="+91"+etPhone.getText().toString();

                if (etPhone.getText().toString().isEmpty()||occopastion.isEmpty())
                {
                    return;
                }


                String Phone = sharedpreferences.getString(UserDetails.phone, "");
                String loginkey = sharedpreferences.getString(UserDetails.loginkey, "");
                String logintype=sharedpreferences.getString(UserDetails.logintype, "");

                //if(Phone.trim().contains(etPhone.getText().toString().trim()))
            if(Phone.contains(etPhone.getText().toString()))
                {



                    if(logintype.contains("Service Provider"))
                    {
                        UserDetails.userType="Service Provider";
                        UserDetails.username=etPhone.getText().toString();
                        //UserDetails.cit_lang=" ";
                       // UserDetails.cit_lati=" ";
                         Double s_lati=12.840711;
                         Double s_lang=77.676369;
                         UserDetails.ser_lati=Double.toString(s_lati);
                         UserDetails.ser_lang=Double.toString(s_lang);;
                        startActivity(new Intent(Login_FireBase.this, TaskAssignment.class));
                     //   finish();
                       // UserDetails.userType="Service Provider";
                       // startActivity(new Intent(Login_FireBase.this, RegistrationForm.class));
                        //finish();
                       // getApplicationContext().startActivity(new Intent(Login_FireBase.this, TaskAssignment.class));
                      //  break;
                    }
                    else
                    {
                        UserDetails.userType="Citizen";
                        UserDetails.username=etPhone.getText().toString();

                        UserDetails.cit_lang=com_lang;
                        UserDetails.cit_lati=com_lati;
                        //UserDetails.ser_lang=" ";
                        //UserDetails.ser_lati=" ";

                        startActivity(new Intent(Login_FireBase.this, CitizenDashboard.class));//ImageGrid.class));
                    }
                }
                else
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(UserDetails.phone, etPhone.getText().toString());
                    editor.putString(UserDetails.loginkey, "complete");
                    editor.putString(UserDetails.logintype, occopastion);
                    editor.commit();


                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            Phonenumber,//etPhone.getText().toString(),        // Phone number to verify
                            1,                 // Timeout duration
                            TimeUnit.MINUTES,   // Unit of timeout
                            this,               // Activity (for callback binding)
                            mCallbacks);
                }
 //////testing purpose
         /*       if (occopastion.contains("Service Provider"))
                {
                    // finish();
                    UserDetails.userType="Service Provider";
                    UserDetails.username=etPhone.getText().toString();
                    startActivity(new Intent(Login_FireBase.this, RegistrationForm.class));
                }
                else
                {
                    // finish();
                    UserDetails.userType="Citizen";
                    UserDetails.username=etPhone.getText().toString();
                    startActivity(new Intent(Login_FireBase.this, ImageGrid.class));
                }*/

//////testing purpose


                break;
            case R.id.resend:
                break;
            case R.id.verified:
                occopastion=occpas_spinner.getSelectedItem().toString();

                if (etOtp.getText().toString().isEmpty()||occopastion.isEmpty())
                {
                    return;
                }
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, etOtp.getText().toString());

                System.out.println("Abhijit The phone Number...."+etPhone.getText().toString()+"   "+occopastion);

                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = task.getResult().getUser();
                                    Toast.makeText(Login_FireBase.this, "Verification Success", Toast.LENGTH_SHORT).show();
                                    addNewContact(user.getPhoneNumber());
                                    AppGlobalSetting.login_category=occopastion;
                                    if (occopastion.contains("Service Provider"))
                                    {

                                        finish();
                                        UserDetails.userType="Service Provider";
                                        startActivity(new Intent(Login_FireBase.this, RegistrationForm.class));
                                    }
                                    else
                                    {
                                        finish();
                                        UserDetails.userType="Citizen";
                                        startActivity(new Intent(Login_FireBase.this, ImageGrid.class));
                                    }
                                } else {
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        Toast.makeText(Login_FireBase.this, "Verification Failed, Invalid credentials", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        });
                break;

/*                case R.id.bt_sign_out:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(FirebasePhoneActivity.this, "Signing out ", Toast.LENGTH_SHORT).show();
                break;*/

        }
    }

    private void addNewContact(String phNo) {

        FirebaseDBModel_Provider pbdata=new FirebaseDBModel_Provider();
        pbdata.setName("dummy");
        pbdata.setAddress("dummy");
        pbdata.setContact_number(phNo);
        pbdata.setIsonline(true);
        pbdata.setOccupassion(occopastion);

        db.collection("PhoneBook").document("Contacts").set(pbdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Login_FireBase.this, "User Registered",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login_FireBase.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }


    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                   // Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    com_lang=Double.toString(currentLocation.getLongitude());
                    com_lati=Double.toString(currentLocation.getLatitude());
                    System.out.println("Abhijit Your Lat...."+currentLocation.getLatitude()+"  Your Lang  "+currentLocation.getLongitude());
                    System.out.println("Abhijit Your Lat...."+com_lati+"  Your Lang  "+com_lang);
                   // SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap);
                   // assert supportMapFragment != null;
                   // supportMapFragment.getMapAsync(MainActivity.this);
                }
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                System.out.println("getLocation() Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
