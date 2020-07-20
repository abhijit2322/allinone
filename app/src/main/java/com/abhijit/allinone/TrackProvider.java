package com.abhijit.allinone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijit.allinone.model.FirebaseDBModel_Provider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TrackProvider extends AppCompatActivity {
    private static final String NAME_KEY = "Name";
    private static final String EMAIL_KEY = "Email";
    private static final String PHONE_KEY = "Phone";
    FirebaseFirestore db;
    TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_provider);

        db = FirebaseFirestore.getInstance();
        textDisplay = findViewById(R.id.textDisplay);
        addNewContact();
        ReadSingleContact();
        UpdateData();
    }

    private void addNewContact() {

        FirebaseDBModel_Provider pbdata=new FirebaseDBModel_Provider();
        pbdata.setName("Abhijit1");
        pbdata.setAddress("Bangalore");
        pbdata.setContact_number("9740856008");
        pbdata.setIsonline(true);

        db.collection("PhoneBook").document("Contacts").set(pbdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TrackProvider.this, "User Registered",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TrackProvider.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

    private void ReadSingleContact() {

        DocumentReference user = db.collection("PhoneBook").document("Contacts");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    //FirebaseDBModel_Provider pbdat=(FirebaseDBModel_Provider)task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("Name: ").append(doc.get("name"));
                    fields.append("\nEmail: ").append(doc.get("address"));
                    fields.append("\nPhone: ").append(doc.get("contact_number"));
                    fields.append("\nOnline status: ").append(doc.get("isonline"));
                    textDisplay.setText(fields.toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    private void UpdateData() {
        DocumentReference contact = db.collection("PhoneBook").document("Contacts");
       /* contact.update(NAME_KEY, "Kenny");
        contact.update(EMAIL_KEY, "kenny@gmail.com");*/
        contact.update("isonline", "false")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TrackProvider.this, "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
