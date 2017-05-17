package com.apporio.demotaxiappdriver.fcmclasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.MainActivity;
import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.newridesync.NewRideSync;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.Constants;
import com.apporio.demotaxiappdriver.parsing.SyncModule;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.apporio.demotaxiappdriver.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyFirebaseMessagingService extends FirebaseMessagingService implements ApiFetcher {

    Intent intent;
    String pn_message, pn_ride_id, pn_ride_status, app_id;
    String driver_id, language_id;
    SessionManager sessionManager;
    LanguageManager languageManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

//        Logger.e("message       " + remoteMessage.getNotification().getBody());
        pn_message = remoteMessage.getData().get("message");
        pn_ride_id = remoteMessage.getData().get("ride_id");
        pn_ride_status = remoteMessage.getData().get("ride_status");
        app_id = remoteMessage.getData().get("app_id");
        Logger.e("pn_message       " + pn_message);
        Logger.e("pn_ride_id       " + pn_ride_id);
        Logger.e("pn_ride_status       " + pn_ride_status);
        Logger.e("app_id       " + app_id);

        if (app_id.equals("2")) {
            checkStatus();
        } else {
//            Ignore this part
        }
    }

    private void checkStatus() {

        if (app_id.equals("2")) {

            sessionManager = new SessionManager(this);
            languageManager = new LanguageManager(this);
            driver_id = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
            language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
            if (pn_ride_status.equals("1")) {
                SyncModule syncModule = new SyncModule(this);
                syncModule.newRideSyncApi(pn_ride_id, driver_id, language_id);
            } else if (pn_ride_status.equals("2")) {
                if (Constants.directionActivity == 0) {
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.putExtra("ride_id", pn_ride_id);
                    broadcastIntent.putExtra("ride_status", pn_ride_status);
                    broadcastIntent.setAction("GCM_RECEIVED_ACTION_CANCEL_TAXI_DRIVER");
                    sendBroadcast(broadcastIntent);
                } else if (Constants.directionActivity == 1) {
                    sendNotification(pn_message);
                } else {
                    sendNotification(pn_message);
                }
            } else if (pn_ride_status.equals("8")) {

                SyncModule syncModule = new SyncModule(this);
                syncModule.newRideSyncApi(pn_ride_id, driver_id, language_id);
            }
        } else {
//            Ignore this code
        }
    }

    void sendNotification(String message123) {

        if (pn_ride_status.equals("1")) {
            intent = new Intent(this, MainActivity.class)
                    .putExtra("ride_id", pn_ride_id)
                    .putExtra("ride_status", pn_ride_status);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (pn_ride_status.equals("2")) {
            intent = new Intent(this, MainActivity.class)
                    .putExtra("ride_id", pn_ride_id)
                    .putExtra("ride_status", pn_ride_status);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (pn_ride_status.equals("8")) {
            intent = new Intent(this, MainActivity.class)
                    .putExtra("ride_id", pn_ride_id)
                    .putExtra("ride_status", pn_ride_status);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo_100);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo_100)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message123)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(0, notificationBuilder.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo_100);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo_100)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message123)
                    .setAutoCancel(true)
                    .setColor(Color.parseColor("#d7ab0f"))
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    @Override
    public void onAPIRunningState(int a) {

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("New Ride Sync")) {

            NewRideSync newRideSync = new NewRideSync();
            newRideSync = gson.fromJson(response, NewRideSync.class);

            if (newRideSync.getResult().toString().equals("1")) {

                pn_message = newRideSync.getMsg();
                pn_ride_id = newRideSync.getDetails().getRideId();
                pn_ride_status = newRideSync.getDetails().getRideStatus();

                if (app_id.equals("2")) {

                    if (Constants.mainActivity == 0) {
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.putExtra("ride_id", pn_ride_id);
                        broadcastIntent.putExtra("ride_status", pn_ride_status);
                        broadcastIntent.setAction("GCM_RECEIVED_ACTION_TAXI_DRIVER");
                        sendBroadcast(broadcastIntent);
                    } else if (Constants.mainActivity == 1) {
                        sendNotification(pn_message);
                    } else {
                        sendNotification(pn_message);
                    }
                }
            }else if (newRideSync.getResult().toString().equals("0")){

            }
        }

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }
}