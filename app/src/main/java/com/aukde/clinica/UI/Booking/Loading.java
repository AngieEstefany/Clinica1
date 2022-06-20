package com.aukde.clinica.UI.Booking;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.aukde.clinica.R;
import com.aukde.clinica.UI.Credentials.LoginActivity;

public class Loading {
    Activity activity;
     AlertDialog dialog;

   public Loading (Activity myActivity){
        activity = myActivity;
    }
    void startLoading(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress, null));
        dialog = builder.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }

}
