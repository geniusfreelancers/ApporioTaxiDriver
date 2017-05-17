package com.apporio.demotaxiappdriver;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.CustomerSupportModel;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.HelpModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomerSupportActivity extends AppCompatActivity implements ApiFetcher{


    @Bind(R.id.user_name_edt)EditText user_name_edt;
    @Bind(R.id.email_edt) EditText email_edt;
    @Bind(R.id.pone_edt) EditText pone_edt;
    @Bind(R.id.query_edt) EditText query_edt;
    @Bind(R.id.root) LinearLayout root;
    HelpModule helpModule;

    SessionManager sessionManager ;
    ProgressDialog progress_dialoge ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helpModule = new HelpModule(this);
        sessionManager = new SessionManager(this);
        progress_dialoge = new ProgressDialog(this);
        progress_dialoge.setMessage(""+this.getResources().getString(R.string.loading));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);
        ButterKnife.bind(this);
        getSupportActionBar().hide();


        findViewById(R.id.ll_back_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_name_edt.getText().toString().equals("")  || email_edt.getText().toString().equals("") || query_edt.getText().toString().equals("") ){
                    Snackbar.make(root , R.string.please_enter_the_maindatory_details , Snackbar.LENGTH_SHORT).show();
                }else {
                    helpModule.customerSupportAPI(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),user_name_edt.getText().toString() , user_name_edt.getText().toString() ,pone_edt.getText().toString(), query_edt.getText().toString(),"1");
                }
            }
        });

    }

    @Override
    public void onAPIRunningState(int a) {

        if(a == ApiFetcher.KEY_API_IS_STARTED){
            progress_dialoge.show();
        }else {
            progress_dialoge.dismiss();
        }

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if(apiName.equals("customer_support")){
            CustomerSupportModel data_response;
            data_response = gson.fromJson(response, CustomerSupportModel.class);

            if(data_response.getResult() == 1){
                dialogForQueryComplete();
            }
        }

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }



    public void dialogForQueryComplete() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_query_complete);
        dialog.setCancelable(false);

        LinearLayout ll_update = (LinearLayout) dialog.findViewById(R.id.ll_ok);
        ll_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
}
