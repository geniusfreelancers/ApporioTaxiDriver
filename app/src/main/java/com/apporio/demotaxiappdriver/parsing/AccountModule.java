package com.apporio.demotaxiappdriver.parsing;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.Config;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;

import org.json.JSONObject;

import java.io.File;

import com.apporio.demotaxiappdriver.logger.Logger;

/**
 * Created by Bhuvneshwar on 11/2/2016.
 */
public class AccountModule implements Apis {

    ApiFetcher apiFetcher;

    public AccountModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void registerApi(String name, String email, String phone, String password, String city_id, String car_type_id, String car_model_id, String car_number, String language_id) {

        String url = register + "?driver_name=" + name + "&driver_email=" + email + "&driver_phone=" + phone + "&driver_password=" + password + "&city_id=" + city_id + "&car_type_id=" + car_type_id + "&car_model_id=" + car_model_id + "&car_number=" + car_number;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(register)
                .addBodyParameter("driver_name", name)
                .addBodyParameter("driver_email", email)
                .addBodyParameter("driver_phone", phone)
                .addBodyParameter("driver_password", password)
                .addBodyParameter("city_id", city_id)
                .addBodyParameter("car_type_id", car_type_id)
                .addBodyParameter("car_model_id", car_model_id)
                .addBodyParameter("car_number", car_number)
                .addBodyParameter("language_id", language_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response          " + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Register");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void registerDocsApi(String driver_id, String insurance, String license, String rc, String driver_token, String language_id) {

        String url = registerDocs + "?driver_id=" + driver_id + "&insurance=" + insurance + "&license=" + license + "&rc=" + rc;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.upload(registerDocs)
                .addMultipartParameter("driver_id", driver_id)
                .addMultipartFile("insurance", new File(insurance))
                .addMultipartFile("license", new File(license))
                .addMultipartFile("rc", new File(rc))
                .addMultipartParameter("driver_token", driver_token)
                .addMultipartParameter("language_id", language_id)
                .setPriority(Priority.HIGH)
                .build()

                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.e("response          " + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Register Docs");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void loginApi(String phone_number, String password, String language_id) {

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(login)
                .addBodyParameter("driver_email_phone", phone_number)
                .addBodyParameter("driver_password", password)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Login");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void editProfile(String user_id, String user_name, String user_mobile, String user_image, String driver_token, String language_id) {

        if (user_image.equals("")) {
            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
            AndroidNetworking.post(editProfile)
                    .addBodyParameter("driver_id", user_id)
                    .addBodyParameter("driver_name", user_name)
                    .addBodyParameter("driver_phone", user_mobile)
                    .addBodyParameter("driver_token", driver_token)
                    .addBodyParameter("language_id", language_id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            Logger.e("response          " + response);
                            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                            apiFetcher.onFetchComplete("" + response, "Edit Profile");
                        }

                        @Override
                        public void onError(ANError anError) {
                            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                            Logger.e("Error Body        " + anError.getErrorBody());
                            Logger.e("Error Code        " + anError.getErrorCode());
                            Logger.e("Error Detail      " + anError.getErrorDetail());
                            Logger.e("Error Message     " + anError.getMessage());
                            Logger.e("Error Localized   " + anError.getLocalizedMessage());
                        }
                    });
        } else {
            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
            AndroidNetworking.upload(editProfile)
                    .addMultipartParameter("driver_id", user_id)
                    .addMultipartParameter("driver_name", user_name)
                    .addMultipartParameter("driver_phone", user_mobile)
                    .addMultipartFile("driver_image", new File(user_image))
                    .addMultipartParameter("driver_token", driver_token)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            Logger.e("response          " + response);
                            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                            apiFetcher.onFetchComplete("" + response, "Edit Profile");
                        }

                        @Override
                        public void onError(ANError anError) {
                            apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                            Logger.e("Error Body        " + anError.getErrorBody());
                            Logger.e("Error Code        " + anError.getErrorCode());
                            Logger.e("Error Detail      " + anError.getErrorDetail());
                            Logger.e("Error Message     " + anError.getMessage());
                            Logger.e("Error Localized   " + anError.getLocalizedMessage());
                        }
                    });
        }
    }

    public void cpApi(String driver_id, String o_p, String n_p, String driver_token, String language_id) {

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(changePassword)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("old_password", o_p)
                .addBodyParameter("new_password", n_p)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Change Password");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void fpApi(String email, String language_id) {

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(forgotPassword)
                .addBodyParameter("driver_email", email)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Forgot Password");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void deviceIdApi(String driver_id, String d_id, String driver_token, String language_id) {

        AndroidNetworking.post(deviceId)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("device_id", d_id)
                .addBodyParameter("flag", ""+ Config.Devicetype)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Device Id");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void logoutApi(String driver_id, String driver_token, String language_id) {

        String url = logout + "?driver_id=" + driver_id + "&driver_token=" + driver_token + "&language_id=" + language_id;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(logout)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Logout");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

}
