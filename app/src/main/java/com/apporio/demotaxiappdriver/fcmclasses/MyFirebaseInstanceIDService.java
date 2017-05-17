package com.apporio.demotaxiappdriver.fcmclasses;

import com.apporio.demotaxiappdriver.manager.DeviceManager;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static String refreshedToken;

    DeviceManager deviceManager;

    @Override
    public void onTokenRefresh() {
//        refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Logger.e("REGISTRATION TOKEN            " + refreshedToken);
//        sendRegistrationToServer(refreshedToken);
    }

//    private void sendRegistrationToServer(String token) {
//        deviceManager = new DeviceManager(MyFirebaseInstanceIDService.this);
//        deviceManager.createDeviceTokenSession(token);
//    }
}
