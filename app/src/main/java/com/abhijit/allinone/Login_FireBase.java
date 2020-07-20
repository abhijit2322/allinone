package com.abhijit.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhijit.allinone.model.FirebaseDBModel_Provider;
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
    Button btSendOtp, btResendOtp, btVerifyOtp;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);
        initFields();
        mAuth = FirebaseAuth.getInstance();
        initFireBaseCallbacks();
    }

    void initFields() {
        etPhone = findViewById(R.id.et_phone);
        etOtp = findViewById(R.id.et_otp);
        btSendOtp = findViewById(R.id.bt_send_otp);
        btResendOtp = findViewById(R.id.bt_resend_otp);
        btVerifyOtp = findViewById(R.id.bt_verify_otp);
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
                System.out.println("Abhijit verification failed message.."+token.toString());
                Toast.makeText(Login_FireBase.this, "Code Sent", Toast.LENGTH_SHORT).show();
                mVerificationId = verificationId;
            }
        };
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send_otp:
                System.out.println("Abhijit The phone Number...."+etPhone.getText().toString());
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        etPhone.getText().toString(),        // Phone number to verify
                        1,                 // Timeout duration
                        TimeUnit.MINUTES,   // Unit of timeout
                        this,               // Activity (for callback binding)
                        mCallbacks);

                break;
            case R.id.bt_resend_otp:
                break;
            case R.id.bt_verify_otp:
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, etOtp.getText().toString());
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = task.getResult().getUser();
                                    Toast.makeText(Login_FireBase.this, "Verification Success", Toast.LENGTH_SHORT).show();
                                    addNewContact(user.getPhoneNumber());
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

}
