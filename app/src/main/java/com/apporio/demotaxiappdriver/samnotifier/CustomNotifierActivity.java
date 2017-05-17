package com.apporio.demotaxiappdriver.samnotifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;

public class CustomNotifierActivity extends AppCompatActivity {


    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;
    TextView texter_countdown ;
    CountDownTimer my_timer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_notifier);
        texter_countdown = (TextView) findViewById(R.id.texter_countdown);


        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setOngoing(true);

        remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification_views);
        remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.ic_launcher);

//


        my_timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("CUSTOME_NOTIFICSTION" , "****");

                remoteViews.setTextViewText(R.id.notif_title,"Seconds remaining: "+  (millisUntilFinished / 1000));
            }

            public void onFinish() {
                Log.d("notification finish" , "****");
                remoteViews.setProgressBar(R.id.progressBar,100,40,true);
                remoteViews.setTextViewText(R.id.notif_title,"Seconds remaining: 0 ");

            }

        };





        findViewById(R.id.button_show_notif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notification_id = (int) System.currentTimeMillis();

                Intent button_intent = new Intent("button_click");
                button_intent.putExtra("id",notification_id);
                PendingIntent button_pending_event = PendingIntent.getBroadcast(context,notification_id,
                        button_intent,0);

                remoteViews.setOnClickPendingIntent(R.id.button,button_pending_event);

                Intent notification_intent = new Intent(context,CustomNotifierActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setCustomBigContentView(remoteViews)
                        .setContentIntent(pendingIntent);




                notificationManager.notify(notification_id,builder.build());
                my_timer.start(); //call this to start the timer

            }
        });



    }
}
