package com.abhijit.allinone.trackdelivary;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;

import androidx.appcompat.app.AlertDialog;

import com.abhijit.allinone.trackdelivary.util.Constants;

/**
 * Created by rajesh on 5/14/2016.
 */
public class PickOrderDialog extends DialogFragment{

    private String[] orders;

    public void setOrders(String[] orders){
        this.orders = orders;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please select your order")
                .setItems(orders, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                       // intent.putExtra(Constants.ORDER_ID, orders[which]);
                        intent.putExtra(Constants.MAPS_TYPE, "D");
                        startActivity(intent);
                    }
                });

        return builder.create();
    }
}