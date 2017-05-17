package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.viewuserinfo.ViewUserInfo;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.ViewModule;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity implements ApiFetcher, Apis {

    TextView tv_name, tv_phone, tv_user_rating, tv_address;
    ImageView iv_customer;
    public static Activity infoActivity;
    LinearLayout ll_back_client_info;

    ProgressDialog pd;

    SessionManager sessionManager;
    String driver_token;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();
        infoActivity = this;

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        languageManager=new LanguageManager(this);
        language_id=languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        sessionManager = new SessionManager(this);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        ll_back_client_info = (LinearLayout) findViewById(R.id.ll_back_client_info);
        iv_customer = (ImageView) findViewById(R.id.iv_user_profile);
        tv_name = (TextView) findViewById(R.id.tv_user_name);
        tv_phone = (TextView) findViewById(R.id.tv_user_phone);
        tv_user_rating = (TextView) findViewById(R.id.tv_user_rating);
        tv_address = (TextView) findViewById(R.id.tv_address);

        String customerId = super.getIntent().getExtras().getString("customerId");
        tv_address.setText(super.getIntent().getExtras().getString("pickupAddress"));

        ViewModule viewModule = new ViewModule(this);
        viewModule.viewUserInfoApi(customerId, driver_token,language_id);

        ll_back_client_info.setOnClickListener(new View.OnClickListener() {
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

        if (apiName.equals("View User Info")) {

            ViewUserInfo viewUserInfo = new ViewUserInfo();
            viewUserInfo = gson.fromJson(response, ViewUserInfo.class);

            if (viewUserInfo.getResult().toString().equals("1")) {
                String customer_name = viewUserInfo.getDetails().getUserName();
//                String customer_email = viewUserInfo.getDetails().getUserEmail();
                String customer_phone = viewUserInfo.getDetails().getUserPhone();
                String rating = viewUserInfo.getDetails().getRating();
                String image = viewUserInfo.getDetails().getUserImage();

                tv_name.setText(customer_name);
                tv_phone.setText(customer_phone);
                tv_user_rating.setText(rating);

                Picasso.with(InfoActivity.this)
                        .load(Config.app_config.getResponse().getDriver_config().getBase_url() + image)
                        .placeholder(R.drawable.dummy_pic)
                        .error(R.drawable.dummy_pic)
                        .fit()
                        .into(iv_customer);
            } else if (viewUserInfo.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + viewUserInfo.getMsg());
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
