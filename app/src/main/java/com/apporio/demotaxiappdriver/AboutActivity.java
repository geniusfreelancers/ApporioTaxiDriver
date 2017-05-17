package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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

public class AboutActivity extends AppCompatActivity implements ApiFetcher {
    public static Activity about;
    LinearLayout ll_back_about;
    TextView tv_about, tv_version;

    LanguageManager languageManager;
    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        about = this;

        ll_back_about = (LinearLayout) findViewById(R.id.ll_back_about);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_version = (TextView) findViewById(R.id.tv_version);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        HelpModule helpModule = new HelpModule(this);
        helpModule.aboutUsApi(language_id);

        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(AboutActivity.this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version1 = info.versionName;
        tv_version.setText(AboutActivity.this.getResources().getString(R.string.version) + version1);

        ll_back_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        if (apiName.equals("About Us")) {
            About about;
            about = gson.fromJson(response, About.class);

            if (about.getResult().toString().equals("1")) {
                if (language_id.equals("1")) {
                    String desc = about.getDetails().getDescription();
                    tv_about.setText(Html.fromHtml(desc , new ImageGetter(), null));
                } else if (language_id.equals("2")) {
                    String desc = about.getDetails().getDescriptionFrench();
                    tv_about.setText(Html.fromHtml(desc , new ImageGetter(), null));
                } else if (language_id.equals("3")) {
                    String desc = about.getDetails().getDescriptionArabic();
                    tv_about.setText(Html.fromHtml(desc , new ImageGetter(), null));
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




    private class ImageGetter implements Html.ImageGetter {

        public Drawable getDrawable(String source) {
            int id;
            if (source.equals("hughjackman.jpg")) {
                id = R.drawable.app_logo_100;
            }
            else {
                return null;
            }
            Drawable d = getResources().getDrawable(id);
            d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
            return d;
        }
    };


}
