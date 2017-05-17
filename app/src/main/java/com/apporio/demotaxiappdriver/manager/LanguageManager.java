package com.apporio.demotaxiappdriver.manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class LanguageManager {

    SharedPreferences prefDevice;
    SharedPreferences.Editor editorDevice;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME_DEVICE = "DevicePreferences";

    public static final String LANGUAGE_ID = "language_id";

    public LanguageManager(Context context) {
        this._context = context;
        prefDevice = _context.getSharedPreferences(PREF_NAME_DEVICE, PRIVATE_MODE);
        editorDevice = prefDevice.edit();
    }

    public void createLanguageSession(String language_id) {
        editorDevice.putString(LANGUAGE_ID, language_id);
        editorDevice.commit();
    }

    public HashMap<String, String> getLanguageDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(LANGUAGE_ID, prefDevice.getString(LANGUAGE_ID, ""));
        return user;
    }
}