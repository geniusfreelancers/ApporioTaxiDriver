package com.apporio.demotaxiappdriver.locationaltrackerclasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;


import com.apporio.demotaxiappdriver.locationaltrackerclasses.constants.FailType;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.constants.LogType;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.constants.ProviderType;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.constants.RequestCode;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.helper.ContinuousTask;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.helper.LocationUtils;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.helper.LogUtils;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.helper.PermissionManager;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.provider.DefaultLocationProvider;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.provider.GPServicesLocationProvider;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.provider.LocationProvider;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

/**
 * Created by Yahya Bayramoglu on 09/02/16.
 */
public class LocationManager {

    private int locationFrom = ProviderType.NONE;

    private Activity activity;
    private Dialog gpServicesDialog;
    private LocationReceiver listener;
    private LocationConfiguration configuration;
    private LocationProvider activeProvider;

    /**
     * This library contains a lot of log to make tracing steps easier,
     * So you can set the type which one corresponds your requirements.
     * Do not forget to set it to NONE before you publish your application!
     */
    public static void setLogType(@LogType.Level int type) {
        LogUtils.setLogType(type);
    }

    /**
     * To create an instance of this manager you MUST specify a LocationConfiguration
     */
    public LocationManager(LocationConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * This specifies on which activity this manager will run,
     * this also needs to be set before you attempt to get location
     */
    public LocationManager on(Activity activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Specify a LocationReceiver to receive location when it is available,
     * or get knowledge of any other steps in process
     */
    public LocationManager notify(LocationReceiver listener) {
        this.listener = listener;
        return this;
    }

    /**
     * Instead of using DefaultLocationProvider you can create your own,
     * and set it to manager so it will run your LocationProvider.
     * Please refer to {@linkplain DefaultLocationProvider}
     */
    public LocationManager setLocationProvider(LocationProvider provider) {
        if (provider != null) {
            provider.configure(activity, configuration);
        }

        this.activeProvider = provider;
        return this;
    }

    /**
     * Possible to change configuration object on runtime
     */
    public LocationManager setConfiguration(LocationConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    /**
     * Returns configuration object which is defined to this manager
     */
    public LocationConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Google suggests to stop location updates when the activity is no longer in focus
     * http://developer.android.com/training/location/receive-location-updates.html#stop-updates
     */
    public void onPause() {
        if (activeProvider != null) {
            activeProvider.onPause();
        }
        gpServicesSwitchTask.pause();
    }

    /**
     * Restart location updates to keep continue getting locations when activity is back
     */
    public void onResume() {
        if (activeProvider != null) {
            activeProvider.onResume();
        }
        gpServicesSwitchTask.resume();
    }

    /**
     * Release whatever you need to when activity is destroyed
     */
    public void onDestroy() {
        if (activeProvider != null) {
            activeProvider.onDestroy();
        }

        gpServicesSwitchTask.stop();
        gpServicesDialog = null;
        listener = null;
        activity = null;
        activeProvider = null;
        configuration = null;
    }

    /**
     * This is required to check when user handles with Google Play Services error, or enables GPS...
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.GOOGLE_PLAY_SERVICES) {
            // Check whether do we have gpServices now or still not!
            get(false);
        } else {
            if (activeProvider != null && activeProvider.requiresActivityResult()) {
                activeProvider.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    /**
     * Provide requestPermissionResult to manager so the it can handle RuntimePermission
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionManager.onRequestPermissionsResult(activity, permissionListener, requestCode, permissions, grantResults);
    }

    /**
     * To determine whether LocationSession is currently waiting for location or it did already receive one!
     */
    public boolean isWaitingForLocation() {
        if (activeProvider != null) {
            return activeProvider.isWaiting();
        } else {
            return false;
        }
    }

    /**
     * To determine whether the manager is currently displaying any dialog or not
     */
    public boolean isAnyDialogShowing() {
        boolean providersDisplaying = (activeProvider != null && activeProvider.isDialogShowing());
        boolean gpServicesDisplaying = (gpServicesDialog != null && gpServicesDialog.isShowing());
        return gpServicesDisplaying || providersDisplaying;
    }

    /**
     * The only method you need to call to trigger getting location process
     */
    public void get() {
        get(true);
    }

    /**
     * Abort the mission and cancel all location update requests
     */
    public void cancel() {
        if (activeProvider != null) {
            activeProvider.cancel();
        }
    }

    private void get(boolean askForGPServices) {
        if (activity == null)
            throw new RuntimeException("You must specify on which activity this manager runs!");

        if (configuration.shouldNotUseGPServices()) {
            LogUtils.logI("Configuration requires not to use Google Play Services, " +
                    "so skipping that step to Default Location Providers", LogType.GENERAL);
            continueWithDefaultProviders();
            return;
        }

        int gpServicesAvailability = GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(activity);

        if (gpServicesAvailability == ConnectionResult.SUCCESS) {
            LogUtils.logI("GooglePlayServices is available on device.", LogType.GENERAL);
            askForPermission(ProviderType.GOOGLE_PLAY_SERVICES);
        } else {
            LogUtils.logI("GooglePlayServices is NOT available on device.", LogType.IMPORTANT);

            if (askForGPServices) {
                if (configuration.shouldAskForGPServices() &&
                        GoogleApiAvailability.getInstance()
                                .isUserResolvableError(gpServicesAvailability)) {

                    LogUtils.logI("Asking user to handle GooglePlayServices error...", LogType.GENERAL);
                    gpServicesDialog = GoogleApiAvailability.getInstance()
                            .getErrorDialog(activity, gpServicesAvailability,
                                    RequestCode.GOOGLE_PLAY_SERVICES, new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            failed(FailType.GP_SERVICES_NOT_AVAILABLE);
                                        }
                                    });

                    gpServicesDialog.show();
                } else {
                    LogUtils.logI("Either GooglePlayServices error is not resolvable "
                            + "or the configuration doesn't wants us to bother user.", LogType.GENERAL);
                    continueWithDefaultProviders();
                }

            } else {
                LogUtils.logI("GooglePlayServices is NOT available and "
                        + "even though we ask user to handle error, "
                        + "it is still NOT available.", LogType.IMPORTANT);

                // This means get method is called by onActivityResult
                // which we already ask user to handle with gpServices error
                continueWithDefaultProviders();
            }
        }
    }

    private void continueWithDefaultProviders() {
        if (configuration.shouldUseOnlyGPServices()) {
            LogUtils.logI("Because the configuration, we can only use GooglePlayServices, so we abort.", LogType.GENERAL);
            failed(FailType.GP_SERVICES_NOT_AVAILABLE);
        } else {
            LogUtils.logI("Attempting to get location from default providers...", LogType.GENERAL);
            askForPermission(ProviderType.DEFAULT_PROVIDERS);
        }
    }

    private void askForPermission(@ProviderType.Source int locationFrom) {
        this.locationFrom = locationFrom;

        if (PermissionManager.hasPermissions(activity, configuration.getRequiredPermissions())) {
            locationPermissionGranted(true);
        } else {
            LogUtils.logI("Asking for Runtime Permissions...", LogType.GENERAL);
            PermissionManager.requestPermissions(activity, permissionListener,
                    configuration.getRationalMessage(), configuration.getRequiredPermissions());
        }
    }

    private void locationPermissionGranted(boolean alreadyHadPermission) {
        LogUtils.logI("We got permission, getting location...", LogType.GENERAL);

        if (listener != null) {
            listener.onPermissionGranted(alreadyHadPermission);
        }

        if (locationFrom == ProviderType.GOOGLE_PLAY_SERVICES) {
            setLocationProvider(new GPServicesLocationProvider());
            gpServicesSwitchTask.delayed(configuration.getGPServicesWaitPeriod());
        } else {
            // To ensure not to use same provider again!
            setLocationProvider(null);
        }

        getLocation();
    }

    private void getLocation() {
        getActiveProvider().notifyTo(listener);
        getActiveProvider().get();
    }

    private LocationProvider getActiveProvider() {
        if (activeProvider == null) {
            setLocationProvider(new DefaultLocationProvider());
        }
        return activeProvider;
    }

    private void failed(int type) {
        if (listener != null) {
            listener.onLocationFailed(type);
        }
    }

    private final ContinuousTask gpServicesSwitchTask = new ContinuousTask() {

        @Override
        public void run() {
            if (activeProvider instanceof GPServicesLocationProvider && activeProvider.isWaiting()) {
                LogUtils.logI("We couldn't receive location from GooglePlayServices, "
                        + "so switching default providers...", LogType.IMPORTANT);
                cancel();
                continueWithDefaultProviders();
            }
        }

    };

    private final PermissionManager.PermissionListener permissionListener = new PermissionManager.PermissionListener() {

        @Override
        public void onPermissionsGranted(List<String> perms) {
            if (perms.size() == configuration.getRequiredPermissions().length) {
                LogUtils.logI("We have all required permission, moving on fetching location!", LogType.GENERAL);
                locationPermissionGranted(false);
            } else {
                LogUtils.logI("User denied some of required permissions! "
                        + "Even though we have following permissions now, "
                        + "task will still be aborted.\n" + LocationUtils.getStringFromList(perms), LogType.GENERAL);
                failed(FailType.PERMISSION_DENIED);
            }
        }

        @Override
        public void onPermissionsDenied(List<String> perms) {
            LogUtils.logI("User denied required permissions!\n" + LocationUtils.getStringFromList(perms), LogType.IMPORTANT);
            failed(FailType.PERMISSION_DENIED);
        }

        @Override
        public void onPermissionRequestRejected() {
            LogUtils.logI("User didn't even let us to ask for permission!", LogType.IMPORTANT);
            failed(FailType.PERMISSION_DENIED);
        }

    };

}