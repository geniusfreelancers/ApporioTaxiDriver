package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.DoneRideInfo;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.apporio.demotaxiappdriver.samwork.*;
import com.apporio.demotaxiappdriver.samwork.ApiManager;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PriceFareActivity extends AppCompatActivity implements ApiFetcher , ApiManager.APIFETCHER{

    RatingBar rating_bar;
    public static Activity pricefare;

    ProgressDialog pd;

    SessionManager sessionManager;

    String driver_token;

    LanguageManager languageManager;
    String language_id, ride_id;
    TextView  pick_location_txt , drop_location_txt, tv_ride_distance, fare_txt, ride_time_charges_txt, waiting_charge_txt, coupon_price_txt, coupon_code_txt, tv_ride_fare, total_payble_fare_txt_large;
    com.apporio.demotaxiappdriver.samwork.ApiManager apiManager ;
    DoneRideInfo doneRideInfo ;
    LinearLayout coupon_layout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = new ApiManager(this);
        languageManager = new LanguageManager(this);
        setContentView(R.layout.price_fare_new);
        getSupportActionBar().hide();
        pricefare = this;
        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));

        apiManager.execution_method_get(Config.ApiKeys.KEY_View_done_ride_info , Apis.viewDoneRide+"?done_ride_id="+getIntent().getExtras().getString("done_ride_id")+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));


        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        sessionManager = new SessionManager(this);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        pick_location_txt = (TextView) findViewById(R.id.pick_location_txt);
        tv_ride_distance = (TextView) findViewById(R.id.tv_ride_distance);
        drop_location_txt = (TextView) findViewById(R.id.drop_location_txt);
        fare_txt = (TextView) findViewById(R.id.fare_txt);
        ride_time_charges_txt = (TextView) findViewById(R.id.ride_time_charges_txt);
        waiting_charge_txt = (TextView) findViewById(R.id.waiting_charge_txt);
        coupon_price_txt = (TextView) findViewById(R.id.coupon_price_txt);
        coupon_code_txt = (TextView) findViewById(R.id.coupon_code_txt);
        tv_ride_fare = (TextView) findViewById(R.id.tv_ride_fare);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);
        coupon_layout = (LinearLayout) findViewById(R.id.coupon_layout);
        total_payble_fare_txt_large = (TextView) findViewById(R.id.total_payble_fare_txt_large);



        ride_id = super.getIntent().getExtras().getString("ride_id");



        findViewById(R.id.ll_submit_rating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(rating_bar.getRating());

                Logger.e("Rating        " + rating);

                if (rating.equals("0.0")) {
                    Toast.makeText(PriceFareActivity.this, PriceFareActivity.this.getResources().getString(R.string.please_select_stars), Toast.LENGTH_SHORT).show();
                } else {
                    DriverModule driverModule = new DriverModule(PriceFareActivity.this);
                    driverModule.ratingApi(ride_id,sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), getIntent().getExtras().getString("customerId"), rating, driver_token, language_id);
                }
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

        if (apiName.equals("Rating")) {
            DeviceId deviceId = new DeviceId();
            deviceId = gson.fromJson(response, DeviceId.class);

            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            } else if (deviceId.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + deviceId.getMsg());
            } else {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if(APINAME.equals(Config.ApiKeys.KEY_View_done_ride_info)){
            doneRideInfo = new DoneRideInfo();
            doneRideInfo = gson.fromJson(""+script, DoneRideInfo.class);
            if (doneRideInfo.getResult() == 1) {

                fare_txt.setText(""+doneRideInfo.getMsg().getAmount());

                tv_ride_fare.setText(""+doneRideInfo.getMsg().getTotal_amount());
                waiting_charge_txt.setText(""+doneRideInfo.getMsg().getWaiting_price());
                pick_location_txt.setText(""+doneRideInfo.getMsg().getBegin_location());
                drop_location_txt.setText(""+doneRideInfo.getMsg().getEnd_location());
                tv_ride_distance.setText(""+doneRideInfo.getMsg().getDistance());
                ride_time_charges_txt.setText(""+doneRideInfo.getMsg().getRide_time_price());
                total_payble_fare_txt_large.setText(""+doneRideInfo.getMsg().getTotal_amount());
                if(doneRideInfo.getMsg().getCoupons_code().equals("")){
                    coupon_layout.setVisibility(View.GONE);
                }else{
                    coupon_layout.setVisibility(View.VISIBLE);
                    coupon_code_txt.setText("Coupon ("+doneRideInfo.getMsg().getCoupons_code()+")");
                    coupon_price_txt.setText("-"+doneRideInfo.getMsg().getCoupons_price());
                }

            } else {
                Toast.makeText(PriceFareActivity.this, "" + doneRideInfo.getMsg().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
