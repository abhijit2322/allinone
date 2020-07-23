/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abhijit.allinone.FirebaseChat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.abhijit.allinone.BuildConfig;
import com.abhijit.allinone.ImageUpload;
import com.abhijit.allinone.UserDetails;

//import com.firebase.ui.auth.*;
//import com.firebase.ui.auth.BuildConfig;
import com.abhijit.allinone.UserDetails;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.BuildConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.abhijit.allinone.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseMainChatActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final String MESSAGE_LENGTH_KEY = "message_length";
    public static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKER = 2;
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int CAMERA_REQUEST = 235;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private ImageButton mPhotoattachButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;
    private Uri filePath;
    ProgressDialog progressDialog;

    String filename;
    UploadTask upload;
    private Uri fileuri;

    //Firebase Instance Variables
    private FirebaseDatabase database;
    private DatabaseReference mFirebaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mAuth;
    //rivate FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosReference;
    StorageReference ref;
    StorageReference photoReference;
   private FirebaseRemoteConfig mRemoteConfig;

    String REGISTER_NUMBER="93430771993";

    //Firebase reference1, reference2,reference_photo1,reference_photo2;
    //String FIREBASE_URL="https://allinone-2ecf2.firebaseio.com/";
    //String PHOTO_URL="gs://allinone-2ecf2.appspot.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_firebase_chat);

        mUsername = ANONYMOUS;
        Firebase.setAndroidContext(this);

        //Initializing Firebase Object
        database = FirebaseDatabase.getInstance();



        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseReference = database.getReference().child("messages");
        mChatPhotosReference = mFirebaseStorage.getReference().child("images");
        mRemoteConfig = FirebaseRemoteConfig.getInstance();

        System.out.println("This is where the problem is "+mFirebaseReference+ "    "+mChatPhotosReference);


        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mPhotoattachButton= (ImageButton) findViewById(R.id.attachment);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);

        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(FirebaseMainChatActivity.this, "take photo ", Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        mPhotoattachButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(FirebaseMainChatActivity.this, "attach photo ", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                 i.setType("image/jpeg");
                 i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                 startActivityForResult(Intent.createChooser(i, "Complete action using"), RC_PHOTO_PICKER);
             }
         });


        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String messageText = mMessageEditText.getText().toString();

                if(!messageText.equals("")){

                    FriendlyMessage message = new FriendlyMessage(mMessageEditText.getText().toString(), REGISTER_NUMBER, null);
                    mFirebaseReference.push().setValue(message);
                    mMessageEditText.setText("");

                }


            }
        });

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        mRemoteConfig.setConfigSettings(configSettings);

        //Creating Default Config map
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(MESSAGE_LENGTH_KEY, DEFAULT_MSG_LENGTH_LIMIT);
        mRemoteConfig.setDefaults(defaultConfigMap);

        fetchConfig();

        mFirebaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                FriendlyMessage friendlyMessage = snapshot.getValue(FriendlyMessage.class);
                System.out.println("This is where the problem is addChildEventListener  >>>>>...."+friendlyMessage.getPhotoUrl()+" text is >>>>>"+friendlyMessage.getText());
                mMessageAdapter.add(friendlyMessage);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                //signout
                //AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if (true) {
          if (resultCode == RESULT_CANCELED) {
               Toast.makeText(this, "Sign-In Cancelled", Toast.LENGTH_SHORT).show();
               finish();
           }
          else if (requestCode ==CAMERA_REQUEST&& resultCode == RESULT_OK) {
              fileuri = data.getData();
              uploadCameraFile(data);
          }
          else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
               Uri imageUri = data.getData();

               //get the reference to stored file at database
              photoReference = mChatPhotosReference.child(imageUri.getLastPathSegment());

               //upload file to firebase
               photoReference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       photoReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               String downloadUrl = uri.toString();
                               FriendlyMessage message = new FriendlyMessage(null, mUsername, downloadUrl.toString());
                               mFirebaseReference.push().setValue(message);
                               //Do what you need to do with url
                           }
                       });

                   }
               });
           }
       }
   }
    //upload file whch taken from camera
    private void uploadCameraFile(Intent data) {
        //displaying progress dialog while image is uploading
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();

        //getting the storage reference
        photoReference = mChatPhotosReference.child("/images"+ System.currentTimeMillis());


        Bundle extras = data.getExtras();
        Bitmap bmp = (Bitmap) extras.get("data");

        //Change to bitmap
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();


        //ve resmi sisteme gönderiyoruz
        upload = photoReference.putBytes(datas);
        upload.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                //Get upload datas as message


                photoReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println("This is where the problem is uploadCameraFile");
                        String downloadUrl = uri.toString();
                        FriendlyMessage message = new FriendlyMessage(null, mUsername, downloadUrl.toString());
                        mFirebaseReference.push().setValue(message);

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FirebaseMainChatActivity.this, "A failure have detected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }



    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        /*if (mAuthStateListener !=null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }*/
        detachDatabaseReadListener();
        mMessageAdapter.clear();

    }

    @Override
    protected void onResume() {
        super.onResume();
       // mAuth.addAuthStateListener(mAuthStateListener);
    }



    protected void OnSignedOut() {
        mUsername = "Anonymous";
        mMessageAdapter.clear();
        detachDatabaseReadListener();
    }




    protected void detachDatabaseReadListener() {

        if (mChildEventListener !=null) {
            mFirebaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }

    }

    public void fetchConfig() {
        long cacheExpiration = 3600;
        if (mRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mRemoteConfig.fetch(cacheExpiration).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mRemoteConfig.activateFetched();
                applyRetrievedLength();;


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error fetching Config", e);
                applyRetrievedLength();
            }
        });
    }

    private void applyRetrievedLength() {
        Long message_length = mRemoteConfig.getLong(MESSAGE_LENGTH_KEY);
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(message_length.intValue())});
        Log.d(TAG, MESSAGE_LENGTH_KEY + " = " + message_length);
    }


}
