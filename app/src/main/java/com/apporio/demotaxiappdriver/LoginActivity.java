package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.register.Register;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginActivity extends AppCompatActivity implements ApiFetcher {

    LinearLayout ll_back_login, ll_login, ll_forgot, ll_login_testing;
    EditText edt_phone_login, edt_pass_login;
    TextView tv_forgot;

    public static Activity loginactivity1;

    ProgressDialog pd;



    LanguageManager languageManager;
    String language_id;

    FirebaseUtils firebaseUtils ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUtils = new FirebaseUtils(this);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginactivity1 = this;

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        ll_back_login = (LinearLayout) findViewById(R.id.ll_back_login);
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        edt_phone_login = (EditText) findViewById(R.id.edt_phone_login);
        edt_pass_login = (EditText) findViewById(R.id.edt_pass_login);

        edt_pass_login.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_phone_login.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));

        ll_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = edt_phone_login.getText().toString().trim();
                String pass = edt_pass_login.getText().toString().trim();

                if (phone.equals("")) {
                    Toast.makeText(LoginActivity.this,LoginActivity.this.getResources().getString(R.string.email_can_not_be_empty) , Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(LoginActivity.this,LoginActivity.this.getResources().getString(R.string.password_can_not_be_empty) , Toast.LENGTH_SHORT).show();
                } else {
                    AccountModule accountModule = new AccountModule(LoginActivity.this);
                    accountModule.loginApi(phone, pass, language_id);
                }
            }
        });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
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

        if (apiName.equals("Login")) {

            Log.d("****** LOGIN_RESPONSE" , ""+response);
            Register register;
            register = gson.fromJson(response, Register.class);

            if (register.getResult().toString().equals("1")) {

                String driver_id = register.getDetails().getDriverId();
                String detail_status = register.getDetails().getDetailStatus();

                new SessionManager(this).createLoginSession(register.getDetails().getDriverId(),register.getDetails().getDriverName(),register.getDetails().getDriverPhone(),register.getDetails().getDriverEmail(),register.getDetails().getDriverImage(),register.getDetails().getDriverPassword(),register.getDetails().getDriverToken(),register.getDetails().getDeviceId(),Config.Devicetype,register.getDetails().getRating(),register.getDetails().getCarTypeId(),register.getDetails().getCarModelId(),
                        register.getDetails().getCarNumber(),register.getDetails().getCityId(),register.getDetails().getRegisterDate(),register.getDetails().getLicense(),register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",register.getDetails().getCompletedRides(), register.getDetails().getRejectRides(),register.getDetails().getCancelledRides(),
                        register.getDetails().getLoginLogout(),register.getDetails().getBusy(),register.getDetails().getOnlineOffline(),register.getDetails().getDetailStatus(),register.getDetails().getStatus(),register.getDetails().getCarTypeName(),register.getDetails().getCarModelName());
//                startActivity(new Intent(LoginActivity.loginactivity1, MainActivity.class));

                firebaseUtils.setUpDriver();


                if (detail_status.equals("1")) {
                    startActivity(new Intent(LoginActivity.this, DocumentActivity.class));
                } else if (detail_status.equals("2")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                SplashActivity.splash.finish();
            } else {
                Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
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
