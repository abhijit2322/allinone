package com.abhijit.allinone.trackdelivary;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abhijit.allinone.R;
import com.abhijit.allinone.UserDetails;
import com.abhijit.allinone.trackdelivary.util.Constants;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

public class OrderDetailActivity extends AppCompatActivity {

    TextView orderIdText, orderDelBoy, orderUser, locationText;
    TextView itemInOrderText;
    TextView statusText;
    ImageView staticUserLocationImage;
    String orderId, item, status, userId, delBoyId, mapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        staticUserLocationImage = (ImageView) findViewById(R.id.user_location_static_map);
        mapType = getIntent().getStringExtra(Constants.MAPS_TYPE);
        processOrder();

        Intent intent = new Intent(OrderDetailActivity.this, MapsActivity.class);
        intent.putExtra(Constants.MAPS_TYPE,mapType);
        intent.putExtra(Constants.CURRENT_USER, "Abhijit1");//userId);
        intent.putExtra(Constants.CURRENT_DELBOY, "Abhijit2");//delBoyId);
        startActivity(intent);

        staticUserLocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Map Activity
                Intent intent = new Intent(OrderDetailActivity.this, MapsActivity.class);
                intent.putExtra(Constants.MAPS_TYPE,mapType);
                intent.putExtra(Constants.CURRENT_USER, "Abhijit1");//userId);
                intent.putExtra(Constants.CURRENT_DELBOY, "Abhijit2");//delBoyId);
                startActivity(intent);
            }
        });


/*
        if(getIntent().hasExtra(Constants.ORDER_ID)) {
            orderId = null;//getIntent().getStringExtra(Constants.ORDER_ID);
            mapType = getIntent().getStringExtra(Constants.MAPS_TYPE);

            if(orderId != null) {
                //Realm realm = Realm.getDefaultInstance();
                //Order order = realm.where(Order.class).equalTo("orderId", orderId).findFirst();
                //final User user = realm.where(User.class).equalTo("currentOrderId", orderId).findFirst();

             *//*   orderIdText = (TextView) findViewById(R.id.order_detail_id);
                itemInOrderText = (TextView) findViewById(R.id.order_detail_item);
                statusText = (TextView) findViewById(R.id.order_detail_status);
                staticUserLocationImage = (ImageView) findViewById(R.id.user_location_static_map);
                orderDelBoy = (TextView) findViewById(R.id.order_delBoy);
                orderUser = (TextView) findViewById(R.id.order_user);
                locationText = (TextView) findViewById(R.id.order_details_location_text);
*//*


                Firebase currentOrderRef = Constants.orderRef.child("/" + orderId);
                currentOrderRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        item = (String) dataSnapshot.child("item").getValue();
                        status = (String) dataSnapshot.child("status").getValue();
                        userId = (String) dataSnapshot.child("userId").getValue();
                        delBoyId = (String) dataSnapshot.child("delBoyId").getValue();

                        orderIdText.setText(orderId);
                        itemInOrderText.setText(item);
                        if(status.equals(Constants.STATUS_GOING_TO_PICKUP)) {
                            statusText.setText("Going to pickup");
                        } else if (status.equals(Constants.STATUS_PICKEDUP)) {
                            statusText.setText("Item picked up");
                        } else if(status.equals(Constants.STATUS_DELIVERED)) {
                            statusText.setText("Item Delivered");
                        }
                        orderDelBoy.setText(delBoyId);
                        orderUser.setText(userId);

                        if (!status.equals(Constants.STATUS_DELIVERED)) {
                            locationText.setVisibility(View.VISIBLE);
                            if (mapType.equals("D")) {
                                Firebase currentUserRef = Constants.userRef.child("/" + userId);
                                currentUserRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String latLan = (String) dataSnapshot.child("currentLocation").getValue();
                                        String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyBP_hEMi4Pu0VGFRRzeFH4Podfkf2qGEks";
                                       // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                                        Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        Toast.makeText(OrderDetailActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (mapType.equals("U")) {
                                Firebase currentDelBoyRef = Constants.delboyRef.child("/" + delBoyId);
                                currentDelBoyRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String latLan = (String) dataSnapshot.child("currentLocation").getValue();
                                        String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyBP_hEMi4Pu0VGFRRzeFH4Podfkf2qGEks";
                                       // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                                        Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        Toast.makeText(OrderDetailActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            staticUserLocationImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //Go to Map Activity
                                    Intent intent = new Intent(OrderDetailActivity.this, MapsActivity.class);
                                    intent.putExtra(Constants.MAPS_TYPE, mapType);
                                    intent.putExtra(Constants.CURRENT_USER, userId);
                                    intent.putExtra(Constants.CURRENT_DELBOY, delBoyId);
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(OrderDetailActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(this, "Invalid Order", Toast.LENGTH_SHORT).show();
           // finish();
        }*/
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DboyActivity.class);
        intent.putExtra(Constants.CURRENT_DELBOY, Prefs.getString(Constants.CURRENT_DELBOY, null));
        startActivity(intent);
    }*/

    public void processOrder() {
       // orderId = getIntent().getStringExtra(Constants.ORDER_ID);
       // mapType = getIntent().getStringExtra(Constants.MAPS_TYPE);
        if (mapType.equals("D")) {

            try {
               // String latLan = UserDetails.cit_lati + "," + UserDetails.cit_lang;

                LatLng latLan = new LatLng(Double.parseDouble(UserDetails.cit_lati), Double.parseDouble(UserDetails.cit_lang));

                System.out.println("The total lat lang >>>>>>at D>>>>>>>>>" + latLan);

                String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyBP_hEMi4Pu0VGFRRzeFH4Podfkf2qGEks";
                // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 100).centerCrop().into(staticUserLocationImage);
            }
            catch (Exception e)
            {
                System.out.println("Excpetion at D "+e.getMessage());
            }


         /*   Firebase currentDelBoyRef = Constants.delboyRef.child("/" + delBoyId);
            currentDelBoyRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String latLan = (String) dataSnapshot.child("currentLocation").getValue();
                    String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyBP_hEMi4Pu0VGFRRzeFH4Podfkf2qGEks";
                    // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                    Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(OrderDetailActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
        else if (mapType.equals("U")) {

            double test_lati=Double.parseDouble(UserDetails.cit_lati);
            test_lati=test_lati+10;
            String lati_test=Double.toString(test_lati);
            try {
                String latLan = lati_test + "," + UserDetails.cit_lang;
                System.out.println("The total lat lang >>>>>>>>at U >>>>>>>" + latLan);
                String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyAWQV_usmixs7_B_fvAIwq9Nj3IQFdfrK8";
                // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
            }
            catch (Exception r){
                System.out.println("Exception happen at U"+r.getMessage());
            }


          /*  Firebase currentDelBoyRef = Constants.delboyRef.child("/" + delBoyId);
            currentDelBoyRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String latLan = (String) dataSnapshot.child("currentLocation").getValue();
                    String placeUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latLan + "&zoom=17&size=1200x250&maptype=roadmap&markers=color:red%7C" + latLan + "&key=AIzaSyBP_hEMi4Pu0VGFRRzeFH4Podfkf2qGEks";
                    // Picasso.with(OrderDetailActivity.this).load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                    Picasso.get().load(placeUrl).resize(staticUserLocationImage.getWidth(), 300).centerCrop().into(staticUserLocationImage);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(OrderDetailActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                }
            });*/
        }


    }
}
