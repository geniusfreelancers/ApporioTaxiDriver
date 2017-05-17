package com.apporio.demotaxiappdriver.others;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.apporio.demotaxiappdriver.logger.Logger;

public class MyBroadcastReceiverCancel extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String ride_status = intent.getExtras().getString("ride_status");
        String ride_id = intent.getExtras().getString("ride_id");
        Logger.e("id        " + ride_id);
        Logger.e("status        " + ride_status);

        Intent i = new Intent("broadCastNameCancel");
        i.putExtra("ride_id", ride_id);
        i.putExtra("ride_status", ride_status);
        context.sendBroadcast(i);

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
