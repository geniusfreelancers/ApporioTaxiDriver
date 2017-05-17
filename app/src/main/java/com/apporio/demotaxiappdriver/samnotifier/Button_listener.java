package com.apporio.demotaxiappdriver.samnotifier;

/**
 * Created by lenovo-pc on 3/31/2017.
 */

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by filipp on 7/28/2016.
 */
public class Button_listener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context, "Button clicked!", Toast.LENGTH_SHORT).show();
    }
}