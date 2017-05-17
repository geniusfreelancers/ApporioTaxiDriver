package com.apporio.demotaxiappdriver;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.ReasonAdapter;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.cancelreasoncustomer.CancelReasonCustomer;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.models.rideaccept.RideAccept;
import com.apporio.demotaxiappdriver.models.viewrideinfodriver.ViewRideInfoDriver;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.parsing.RideModule;
import com.apporio.demotaxiappdriver.trackride.TrackRideActivity;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class SelectedRidesActivity extends AppCompatActivity implements ApiFetcher, Apis {

    ImageView iv_image_user;
    TextView tv_name_user, tv_rupee, tv_dis, tv_time1, tv_start_location, tv_end_location, tv_tota, tv_date_time1;
    LinearLayout ll_back_ride_select, ll_accept_ride, ll_reject_ride, ll_cancel_ride, ll_track_ride, ll_user_detail, ll_bill, ll_location_module, ll_total_bill, ll_call_user;
    RatingBar rating_selected;

    ProgressDialog pd;

    String pickup_lat, pickup_long, pickup_location, drop_lat, drop_long, drop_location, user_name, user_image, user_phone,
            rating, amount, distance, time, begin_lat, begin_long, begin_location, begin_time,
            arrived_time, end_lat, end_long, end_location, driver_id, user_id,ride_id, ride_status, ride_type, date_time;

    CancelReasonCustomer cancelReasonCustomer;

    //    String rideId, pickupLat, pickupLong, userPhone, driverId;
    SessionManager sessionManager;
    RideModule rideModule;
    String driver_token;

    String reason_id;

    LanguageManager languageManager;
    String language_id;
    ViewRideInfoDriver viewRideInfoDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_rides);
        getSupportActionBar().hide();

        sessionManager = new SessionManager(this);
        rideModule = new RideModule(this);
        driver_id = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

//        rideModule = new RideModule(this);

//        iv_map = (ImageView) findViewById(R.id.iv_map);
//        iv_image_car = (ImageView) findViewById(R.id.iv_image_car);
        iv_image_user = (ImageView) findViewById(R.id.iv_image_user);

        tv_name_user = (TextView) findViewById(R.id.tv_name_user);
        tv_rupee = (TextView) findViewById(R.id.tv_rupee);
        tv_dis = (TextView) findViewById(R.id.tv_dis);
        tv_time1 = (TextView) findViewById(R.id.tv_time1);
        tv_start_location = (TextView) findViewById(R.id.tv_start_location);
        tv_end_location = (TextView) findViewById(R.id.tv_end_location);
        tv_tota = (TextView) findViewById(R.id.tv_tota);
        tv_date_time1 = (TextView) findViewById(R.id.tv_date_time1);

        rating_selected = (RatingBar) findViewById(R.id.rating_selected);

        ll_back_ride_select = (LinearLayout) findViewById(R.id.ll_back_ride_select);
        ll_user_detail = (LinearLayout) findViewById(R.id.ll_user_detail);
        ll_bill = (LinearLayout) findViewById(R.id.ll_bill);
        ll_location_module = (LinearLayout) findViewById(R.id.ll_location_module);
        ll_total_bill = (LinearLayout) findViewById(R.id.ll_total_bill);

        ll_accept_ride = (LinearLayout) findViewById(R.id.ll_accept_ride);
        ll_reject_ride = (LinearLayout) findViewById(R.id.ll_reject_ride);
        ll_cancel_ride = (LinearLayout) findViewById(R.id.ll_cancel_ride);
        ll_track_ride = (LinearLayout) findViewById(R.id.ll_track_ride);
        ll_call_user = (LinearLayout) findViewById(R.id.ll_call_user);

        ride_id = super.getIntent().getExtras().getString("ride_id");
        ride_status = super.getIntent().getExtras().getString("ride_status");
        ride_type = super.getIntent().getExtras().getString("ride_type");
        date_time = super.getIntent().getExtras().getString("date_time");
        tv_date_time1.setText(date_time);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        if (ride_status.equals("1")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.VISIBLE);
            ll_reject_ride.setVisibility(View.VISIBLE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);

        } else if (ride_status.equals("2")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("3")) {
            ll_call_user.setVisibility(View.VISIBLE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.VISIBLE);
            ll_track_ride.setVisibility(View.VISIBLE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("4")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("5")) {
            ll_call_user.setVisibility(View.VISIBLE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.VISIBLE);
            ll_track_ride.setVisibility(View.VISIBLE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("6")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.VISIBLE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("7")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.VISIBLE);
            ll_total_bill.setVisibility(View.VISIBLE);
        } else if (ride_status.equals("8")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.VISIBLE);
            ll_reject_ride.setVisibility(View.VISIBLE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        } else if (ride_status.equals("9")) {
            ll_call_user.setVisibility(View.GONE);
            ll_accept_ride.setVisibility(View.GONE);
            ll_reject_ride.setVisibility(View.GONE);
            ll_cancel_ride.setVisibility(View.GONE);
            ll_track_ride.setVisibility(View.GONE);

            ll_location_module.setVisibility(View.VISIBLE);
            ll_user_detail.setVisibility(View.VISIBLE);
            ll_bill.setVisibility(View.GONE);
            ll_total_bill.setVisibility(View.GONE);
        }

        rideModule.viewRideInfoApi(ride_id, driver_token, language_id);

        ll_cancel_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rideModule.cancelReasonApi(language_id);

//                rideModule.cancelRideApi(ride_id,driver_token);
            }
        });

        ll_accept_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rideModule.acceptRideApi(ride_id, driver_id, driver_token, language_id);
            }
        });

        ll_reject_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rideModule.rejectRideApi(ride_id, driver_id, driver_token, language_id);
            }
        });

        ll_call_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + user_phone.trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        ll_back_ride_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_track_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectedRidesActivity.this, TrackRideActivity.class)
                        .putExtra("customer_name" , ""+ viewRideInfoDriver.getDetails().getUserName())
                        .putExtra("customer_phone" ,""+viewRideInfoDriver.getDetails().getUserPhone()));
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

        if (apiName.equals("Cancel Ride")) {
            DeviceId deviceId;
            deviceId = gson.fromJson(response, DeviceId.class);
            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg().toString(), Toast.LENGTH_SHORT).show();
                finish();
//                viewRides.getMsg().get(trial).setRideStatus("2");
//                this.notifyDataSetChanged();
            } else {
                Log.e("err", deviceId.getMsg().toString());
            }
        }

        if (apiName.equals("View Reasons")) {
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(response, ResultCheck.class);
            if (resultCheck.result.equals("1")) {
                cancelReasonCustomer = gson.fromJson(response, CancelReasonCustomer.class);
                showReasonDialog();
            } else {
                Toast.makeText(this, ""+this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Accept Ride")) {
            RideAccept rideAccept;
            rideAccept = gson.fromJson(response, RideAccept.class);

            if (rideAccept.getResult() == 1) {
                String rideId = rideAccept.getDetails().getRide_id();
                String pickupLat = rideAccept.getDetails().getPickup_lat();
                String pickupLong = rideAccept.getDetails().getPickup_long();
                String pickupAddress = rideAccept.getDetails().getPickup_location();
                String dropLat = rideAccept.getDetails().getDrop_lat();
                String dropLong = rideAccept.getDetails().getDrop_long();
                String dropAddress = rideAccept.getDetails().getDrop_location();
                String driverId = rideAccept.getDetails().getDriver_id();
                String userId = rideAccept.getDetails().getUser_id();
                String rideStatus = rideAccept.getDetails().getRide_status();

                startActivity(new Intent(this, DirectionActivity.class)
                        .putExtra("rideId", rideId)
                        .putExtra("pickupLat", pickupLat)
                        .putExtra("pickupLong", pickupLong)
                        .putExtra("pickupAddress", pickupAddress)
                        .putExtra("dropLat", dropLat)
                        .putExtra("dropLong", dropLong)
                        .putExtra("dropAddress", dropAddress)
                        .putExtra("customerId", userId)
                        .putExtra("driverId", driverId)
                        .putExtra("arrived_time", "")
                        .putExtra("begin_lat", "")
                        .putExtra("begin_long", "")
                        .putExtra("begin_location", "")
                        .putExtra("begin_time", "")
                        .putExtra("ride_status", rideStatus)
                );
                finish();
            } else if (rideAccept.getResult() == 419) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + rideAccept.getMsg());
            } else {
                Toast.makeText(this, ""+this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Reject Ride")) {
            DeviceId deviceId;
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


        if (apiName.equals("View Ride Info")) {

            viewRideInfoDriver = gson.fromJson(response, ViewRideInfoDriver.class);

            if (viewRideInfoDriver.getResult().toString().equals("1")) {

                ride_id = viewRideInfoDriver.getDetails().getRideId();
                pickup_lat = viewRideInfoDriver.getDetails().getPickupLat();
                pickup_long = viewRideInfoDriver.getDetails().getPickupLong();
                pickup_location = viewRideInfoDriver.getDetails().getPickupLocation();
                drop_location = viewRideInfoDriver.getDetails().getDropLocation();

                user_id = viewRideInfoDriver.getDetails().getUserId();
                user_name = viewRideInfoDriver.getDetails().getUserName();
                user_image = viewRideInfoDriver.getDetails().getUserImage();
                user_phone = viewRideInfoDriver.getDetails().getUserPhone();
                rating = viewRideInfoDriver.getDetails().getRating();
                amount = viewRideInfoDriver.getDetails().getAmount();
                distance = viewRideInfoDriver.getDetails().getDistance();
                time = viewRideInfoDriver.getDetails().getTime();

                arrived_time = viewRideInfoDriver.getDetails().getArrivedTime();

                begin_lat = viewRideInfoDriver.getDetails().getBeginLat();
                begin_long = viewRideInfoDriver.getDetails().getBeginLong();
                begin_location = viewRideInfoDriver.getDetails().getBeginLocation();
                begin_time = viewRideInfoDriver.getDetails().getBeginTime();

                end_lat = viewRideInfoDriver.getDetails().getEndLat();
                end_long = viewRideInfoDriver.getDetails().getEndLong();
                end_location = viewRideInfoDriver.getDetails().getEndLocation();

                tv_name_user.setText(user_name);
                tv_rupee.setText(amount+" "+this.getResources().getString(R.string.currency_symbol));
                tv_dis.setText(distance + " "+this.getResources().getString(R.string.distance_symbol));
                tv_time1.setText(time);
                tv_start_location.setText(begin_location);
                tv_end_location.setText(end_location);
                tv_tota.setText(amount+" "+this.getResources().getString(R.string.currency_symbol));
//                tv_rating.setText(rating);
                Picasso.with(this)
                        .load(Config.app_config.getResponse().getDriver_config().getBase_url() + user_image)
                        .placeholder(R.drawable.dummy_pic)
                        .error(R.drawable.dummy_pic)
                        .fit()
                        .into(iv_image_user);

                if (rating.equals("")) {
                } else {
                    Float rate = Float.valueOf(rating);
                    rating_selected.setRating(rate);
                }
                if (ride_status.equals("1")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("2")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("3")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("4")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("5")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("6")) {
                    tv_start_location.setText(begin_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("7")) {
                    tv_start_location.setText(begin_location);
                    tv_end_location.setText(end_location);
                } else if (ride_status.equals("8")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                } else if (ride_status.equals("9")) {
                    tv_start_location.setText(pickup_location);
                    tv_end_location.setText(drop_location);
                }
            } else if (viewRideInfoDriver.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + viewRideInfoDriver.getMsg());
            } else {
                Toast.makeText(SelectedRidesActivity.this, ""+this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    public void showReasonDialog() {

        final Dialog dialog = new Dialog(SelectedRidesActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_cancel_reason);

        ListView lv_reasons = (ListView) dialog.findViewById(R.id.lv_reasons);
        lv_reasons.setAdapter(new ReasonAdapter(SelectedRidesActivity.this, cancelReasonCustomer));

        lv_reasons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reason_id = cancelReasonCustomer.getMsg().get(position).getReasonId();
                RideModule rideModule = new RideModule(SelectedRidesActivity.this);
                rideModule.cancelRideApi(ride_id, reason_id, language_id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
