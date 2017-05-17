package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.CarAdapter;
import com.apporio.demotaxiappdriver.adapter.CarModelAdapter;
import com.apporio.demotaxiappdriver.adapter.CityAdapter;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.carmodels.CarModels;
import com.apporio.demotaxiappdriver.models.register.Register;
import com.apporio.demotaxiappdriver.models.viewcartype.ViewCarType;
import com.apporio.demotaxiappdriver.models.viewcity.ViewCity;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.apporio.demotaxiappdriver.parsing.ViewModule;
import com.apporio.demotaxiappdriver.samwork.*;
import com.apporio.demotaxiappdriver.samwork.ApiManager;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RegisterActivity extends AppCompatActivity implements ApiFetcher , com.apporio.demotaxiappdriver.samwork.ApiManager.APIFETCHER{

    TextView tv_car_type, tv_car_model, tv_city;
    EditText edt_username_signup, edt_email_signup, edt_phone_signup, edt_pass_signup, edt_car_number;
    LinearLayout ll_register, ll_back_signup;

    public static Activity register;

    String city_id, city_name, car_id, car_name, car_model_id, car_model_name;

    ProgressDialog pd;

    String cityCheck = "", carTypeCheck = "", carNameCheck = "";

    ViewCity viewCity;
    ViewCarType viewCarType;
    CarModels carModels;
//    ViewModule viewModule;

    LanguageManager languageManager;
    String language_id;

    com.apporio.demotaxiappdriver.samwork.ApiManager apimanager ;

    GsonBuilder builder ;
    Gson gson ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apimanager = new ApiManager(this);
        builder = new GsonBuilder();
        gson = builder.create();
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        register = this;


        pd = new ProgressDialog(this);
        pd.setMessage(RegisterActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_car_type = (TextView) findViewById(R.id.tv_car_type);
        tv_car_model = (TextView) findViewById(R.id.tv_car_model);

        edt_username_signup = (EditText) findViewById(R.id.edt_username_signup);
        edt_email_signup = (EditText) findViewById(R.id.edt_email_signup);
        edt_phone_signup = (EditText) findViewById(R.id.edt_phone_signup);
        edt_pass_signup = (EditText) findViewById(R.id.edt_pass_signup);

        edt_car_number = (EditText) findViewById(R.id.edt_car_number);
        ll_register = (LinearLayout) findViewById(R.id.ll_register);
        ll_back_signup = (LinearLayout) findViewById(R.id.ll_back_signup);

        edt_username_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_email_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_phone_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_pass_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_car_number.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        apimanager.execution_method_get( Config.ApiKeys.KEY_View_cities , Apis.viewCities+"?&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID) );

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cityCheck.equals("1")) {

                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_city);

                    ListView lv_cities = (ListView) dialog.findViewById(R.id.lv_cities);
                    lv_cities.setAdapter(new CityAdapter(RegisterActivity.this, viewCity));


                    lv_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            city_id = viewCity.getMsg().get(position).getCityId();
                            if (language_id.equals("1")) {
                                city_name = viewCity.getMsg().get(position).getCityName();
                            } else if (language_id.equals("2")) {
                                city_name = viewCity.getMsg().get(position).getCityNameFrench();
                            } else if (language_id.equals("3")) {
                                city_name = viewCity.getMsg().get(position).getCityNameArabic();
                            }
                            tv_city.setText(city_name);
                            apimanager.execution_method_get(Config.ApiKeys.KEY_View_car_by_city , Apis.viewCarByCities+"?city_id="+city_id+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (cityCheck.equals("2")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_city_found), Toast.LENGTH_SHORT).show();
                }
            }
        });




        tv_car_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_city.getText().toString().equals("Select City")) {
                    Toast.makeText(RegisterActivity.this,  RegisterActivity.this.getResources().getString(R.string.please_select_city), Toast.LENGTH_SHORT).show();
                } else if (carTypeCheck.equals("1")) {
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_car);

                    ListView lv_cars = (ListView) dialog.findViewById(R.id.lv_cars);
                    lv_cars.setAdapter(new CarAdapter(RegisterActivity.this, viewCarType));

                    lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            car_id = viewCarType.getMsg().get(position).getCarTypeId();

                            if (language_id.equals("1")) {
                                car_name = viewCarType.getMsg().get(position).getCarTypeName();
                            } else if (language_id.equals("2")) {
                                car_name = viewCarType.getMsg().get(position).getCarTypeNameFrench();
                            } else if (language_id.equals("3")) {
                                car_name = viewCarType.getMsg().get(position).getCarNameArabic();
                            }
                            tv_car_type.setText(car_name);
                            apimanager.execution_method_get(Config.ApiKeys.KEY_View_car_Model , Apis.viewCarModels+"?car_type_id="+car_id+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (carTypeCheck.equals("2")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_car_found), Toast.LENGTH_SHORT).show();
                }
            }
        });




        tv_car_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_car_type.getText().toString().equals("Select Car Type")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_type_first), Toast.LENGTH_SHORT).show();
                } else if (carNameCheck.equals("1")) {
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_car_model);

                    ListView lv_cars = (ListView) dialog.findViewById(R.id.lv_car_model);
                    lv_cars.setAdapter(new CarModelAdapter(RegisterActivity.this, carModels));

                    lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            car_model_id = carModels.getMsg().get(position).getCarModelId();

                            if (language_id.equals("1")) {
                                car_model_name = carModels.getMsg().get(position).getCarModelName();
                            } else if (language_id.equals("2")) {
                                car_model_name = carModels.getMsg().get(position).getCarModelNameFrench();
                            } else if (language_id.equals("3")) {
                                car_model_name = carModels.getMsg().get(position).getCarModelNameArabic();
                            }
                            tv_car_model.setText(car_model_name);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                } else {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_car_model_found), Toast.LENGTH_SHORT).show();
                }

            }
        });

        ll_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edt_username_signup.getText().toString().trim();
                String email = edt_email_signup.getText().toString().trim();
                String phone = edt_phone_signup.getText().toString().trim();
                String password = edt_pass_signup.getText().toString().trim();
                String carTypeName = tv_car_type.getText().toString();
                String carModelName = tv_car_model.getText().toString().trim();
                String carNumber = edt_car_number.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";

                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this,RegisterActivity.this.getResources().getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
                } else if (phone.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_phone), Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_password) , Toast.LENGTH_SHORT).show();
                } else if (carTypeName.equals("Select Car Type")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_type), Toast.LENGTH_SHORT).show();
                } else if (carModelName.equals("Select Car Model")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_model), Toast.LENGTH_SHORT).show();
                } else if (carNumber.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_car_number), Toast.LENGTH_SHORT).show();
                } else if (!(email.matches(emailPattern))) {
                    edt_email_signup.setText("");
                    Toast.makeText(getApplicationContext(), RegisterActivity.this.getResources().getString(R.string.please_enter_correct_email), Toast.LENGTH_SHORT).show();
                } else if (phone.length() <9) {
                    Toast.makeText(getApplicationContext(), RegisterActivity.this.getResources().getString(R.string.phone_number_should_be_of_ten_digit) , Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.password_should_be_minimum_six_digit), Toast.LENGTH_SHORT).show();
                } else {
                    apimanager.execution_method_get(Config.ApiKeys.KEY_Driver_register , Apis.register+"?driver_name="+name+"&driver_email="+email+"&driver_phone="+phone+"&driver_password="+password+"&city_id="+city_id+"&car_type_id="+car_id+"&car_model_id="+car_model_id+"&car_number="+carNumber+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }
            }
        });

        ll_back_signup.setOnClickListener(new View.OnClickListener() {
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


        if (apiName.equals("View City")) {
        }



        if (apiName.equals("View Cars Models")) {

        }

        if (apiName.equals("Register")) {
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }



/////////////// samir work
    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiFetcher.KEY_API_IS_STARTED) {
            pd.show();
        }
        if (a == ApiFetcher.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        if(APINAME.equals(Config.ApiKeys.KEY_View_cities)){
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(""+script, ResultCheck.class);
            if (resultCheck.result.toString().equals("1")) {
                cityCheck = "1";
                viewCity = gson.fromJson(""+script, ViewCity.class);
            } else {
                cityCheck = "2";
            }
        }
        if (APINAME.equals(Config.ApiKeys.KEY_View_car_by_city)) {
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(""+script, ResultCheck.class);
            if (resultCheck.result.equals("1")) {
                carTypeCheck = "1";
                viewCarType = gson.fromJson(""+script, ViewCarType.class);
            } else {
                carTypeCheck = "2";
            }
        }
        if (APINAME.equals(Config.ApiKeys.KEY_View_car_Model)){
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(""+script, ResultCheck.class);
            if (resultCheck.result.equals("1")) {
                carNameCheck = "1";
                carModels = gson.fromJson(""+script, CarModels.class);
            } else {
                carNameCheck = "2";
            }
        }
        if (APINAME.equals(Config.ApiKeys.KEY_Driver_register)){
            Register register = new Register();
            register = gson.fromJson(""+script + "", Register.class);
            if (register.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
                new SessionManager(this).createLoginSession(register.getDetails().getDriverId(),register.getDetails().getDriverName(),register.getDetails().getDriverPhone(),register.getDetails().getDriverEmail(),register.getDetails().getDriverImage(),register.getDetails().getDriverPassword(),register.getDetails().getDriverToken(),register.getDetails().getDeviceId(),Config.Devicetype,register.getDetails().getRating(),register.getDetails().getCarTypeId(),register.getDetails().getCarModelId(),
                        register.getDetails().getCarNumber(),register.getDetails().getCityId(),register.getDetails().getRegisterDate(),register.getDetails().getLicense(),register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",register.getDetails().getCompletedRides(), register.getDetails().getRejectRides(),register.getDetails().getCancelledRides(),
                        register.getDetails().getLoginLogout(),register.getDetails().getBusy(),register.getDetails().getOnlineOffline(),register.getDetails().getDetailStatus(),register.getDetails().getStatus(),register.getDetails().getCarTypeName(),register.getDetails().getCarModelName());
                startActivity(new Intent(this, DocumentActivity.class));
//                firebaseUtils.setUpDriver(register);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                SplashActivity.splash.finish();
            } else {
                Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}