package com.apporio.demotaxiappdriver.manager;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
public class DeviceManager {

    SharedPreferences prefDevice;
    SharedPreferences.Editor editorDevice;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME_DEVICE = "DevicePreferences";

    public static final String DEVICE_TOKEN = "device_token";

    public DeviceManager(Context context) {
        this._context = context;
        prefDevice = _context.getSharedPreferences(PREF_NAME_DEVICE, PRIVATE_MODE);
        editorDevice = prefDevice.edit();
    }

    public void createDeviceTokenSession(String device_token) {
        editorDevice.putString(DEVICE_TOKEN, device_token);
        editorDevice.commit();
    }

    public HashMap<String, String> getDeviceTokenDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(DEVICE_TOKEN, prefDevice.getString(DEVICE_TOKEN, ""));
        return user;
    }
}