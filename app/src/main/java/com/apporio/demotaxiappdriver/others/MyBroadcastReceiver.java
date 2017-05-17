package com.apporio.demotaxiappdriver.others;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.logger.Logger;

import java.io.IOException;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {

        String ride_status = intent.getExtras().getString("ride_status");
        String ride_id = intent.getExtras().getString("ride_id");
        Logger.e("id        " + ride_id);
        Logger.e("status        " + ride_status);

//        Intent i = new Intent("broadCastName");
//        i.putExtra("ride_id", ride_id);
//        i.putExtra("ride_status", ride_status);
//        context.sendBroadcast(i);

//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(context, notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notification_tone));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        Intent i = new Intent();
        i.setClassName("com.apporio.demotaxiappdriver", "com.apporio.demotaxiappdriver.ReceivePassengerActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("rideId", ride_id);
        context.startActivity(i);
    }
}
