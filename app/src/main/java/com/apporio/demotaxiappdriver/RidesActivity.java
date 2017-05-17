package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.RidesAdapter;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.viewrides.ViewRides;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.RideModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RidesActivity extends AppCompatActivity implements ApiFetcher {

    LinearLayout ll_back_rides;
    ListView lv_all_rides;
    TextView tv_all_rides;
    public static Activity ridesActivity;

    SessionManager sessionManager;

    ProgressDialog pd;

    ViewRides viewRides;
    String driver_token;


    LanguageManager languageManager;
    String language_id,driver_id;

    RideModule rideModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);
        getSupportActionBar().hide();

        ridesActivity = this;
        viewRides = new ViewRides();

        pd = new ProgressDialog(this);
        pd.setMessage(RidesActivity.this.getResources().getString(R.string.loading));

        languageManager=new LanguageManager(this);
        language_id=languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        sessionManager = new SessionManager(this);
         driver_id = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        driver_token=sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        ll_back_rides = (LinearLayout) findViewById(R.id.ll_back_rides);
        tv_all_rides = (TextView) findViewById(R.id.tv_all_rides);

        lv_all_rides = (ListView) findViewById(R.id.lv_all_rides);
        lv_all_rides.setDivider(null);

         rideModule = new RideModule(this);
        rideModule.myRidesApi(driver_id,driver_token,language_id);

        lv_all_rides.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ride_id = viewRides.getMsg().get(position).getRideId();
                String ride_status = viewRides.getMsg().get(position).getRideStatus();
                String ride_type = viewRides.getMsg().get(position).getRideType();
                String date_time = viewRides.getMsg().get(position).getRideDate() + ", " + viewRides.getMsg().get(position).getRideTime();

                startActivity(new Intent(RidesActivity.this, SelectedRidesActivity.class).putExtra("ride_id", ride_id)
                        .putExtra("ride_status", ride_status)
                        .putExtra("date_time", date_time)
                        .putExtra("ride_type", ride_type));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

            }
        });

        ll_back_rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        rideModule.myRidesApi(driver_id,driver_token,language_id);
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

        Log.d("########" , ""+response);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("My Rides")) {

            ResultCheck resultCheck;
            resultCheck = gson.fromJson(response, ResultCheck.class);

            if (resultCheck.result.equals("1")) {
                viewRides = gson.fromJson(response, ViewRides.class);
                tv_all_rides.setVisibility(View.GONE);
                lv_all_rides.setVisibility(View.VISIBLE);
                lv_all_rides.setAdapter(new RidesAdapter(this, viewRides));
            } else if (resultCheck.result.equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
//                Logger.e("lat long update       " + viewRides.getMsg());
            }else {
                tv_all_rides.setVisibility(View.VISIBLE);
                lv_all_rides.setVisibility(View.GONE);
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
