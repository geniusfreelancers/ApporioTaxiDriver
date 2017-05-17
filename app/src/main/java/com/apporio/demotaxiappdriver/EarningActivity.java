package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.EarningAdapter;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.earnings.Earnings;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.RideModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EarningActivity extends AppCompatActivity implements ApiFetcher {

    LinearLayout ll_back_earnings;
    ListView lv_earnings;
    TextView tv_earnings;
    public static Activity ridesActivity;

    SessionManager sessionManager;
    ProgressDialog pd;

    Earnings earnings;

    String driver_token;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        getSupportActionBar().hide();

        ridesActivity = this;
        languageManager=new LanguageManager(this);
        language_id=languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);


        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");

        sessionManager = new SessionManager(this);
        String driver_id = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        ll_back_earnings = (LinearLayout) findViewById(R.id.ll_back_earnings);
        tv_earnings = (TextView) findViewById(R.id.tv_earnings);

        lv_earnings = (ListView) findViewById(R.id.lv_earnings);
        lv_earnings.setDivider(null);

        RideModule rideModule = new RideModule(this);
        rideModule.earningsApi(driver_id, driver_token,language_id);

        ll_back_earnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onAPIRunningState(int a) {

        if (a == ApiFetcher.KEY_API_IS_RUNNING) {
            pd.show();
        }
        if (a == ApiFetcher.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("My Earnings")) {

            ResultCheck resultCheck;
            resultCheck = gson.fromJson(response, ResultCheck.class);

            if (resultCheck.result.equals("1")) {

                earnings = gson.fromJson(response, Earnings.class);
                tv_earnings.setVisibility(View.GONE);
                lv_earnings.setVisibility(View.VISIBLE);
                lv_earnings.setAdapter(new EarningAdapter(this, earnings));
            } else if (resultCheck.result.equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + earnings.getMsg());
            } else {
                tv_earnings.setVisibility(View.VISIBLE);
                lv_earnings.setVisibility(View.GONE);
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
