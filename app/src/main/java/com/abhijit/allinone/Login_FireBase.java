package com.abhijit.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login_FireBase extends AppCompatActivity implements View.OnClickListener {

    EditText etPhone, etOtp;
    Button btSendOtp, btResendOtp, btVerifyOtp;
    private FirebaseAuth mAuth;
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

}
