package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.about.About;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.HelpModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TcActivity extends AppCompatActivity implements ApiFetcher {

    LinearLayout bck;
    TextView tv_desc;
    public static Activity tca;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc);
        getSupportActionBar().hide();
        tca = this;

        bck = (LinearLayout) findViewById(R.id.bck);
        tv_desc = (TextView) findViewById(R.id.tc);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        HelpModule helpModule = new HelpModule(this);
        helpModule.tcApi(language_id);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onAPIRunningState(int a) {

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("T and C")) {
            About about;
            about = gson.fromJson(response, About.class);

            if (about.getResult().toString().equals("1")) {
                if (language_id.equals("1")) {
                    String desc = about.getDetails().getDescription();
                    tv_desc.setText(desc);
                } else if (language_id.equals("2")) {
                    String desc = about.getDetails().getDescriptionFrench();
                    tv_desc.setText(desc);
                } else if (language_id.equals("3")) {
                    String desc = about.getDetails().getDescriptionArabic();
                    tv_desc.setText(desc);
                }
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
