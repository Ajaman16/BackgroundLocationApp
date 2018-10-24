
package com.thunderx.locationapp.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

import java.util.List;

/**
 * Receiver for handling location updates.
 *
 * For apps targeting API level O
 * {@link android.app.PendingIntent#getBroadcast(Context, int, Intent, int)} should be used when
 * requesting location updates. Due to limits on background services,
 * {@link android.app.PendingIntent#getService(Context, int, Intent, int)} should not be used.
 *
 *  Note: Apps running on "O" devices (regardless of targetSdkVersion) may receive updates
 *  less frequently than the interval specified in the
 *  {@link com.google.android.gms.location.LocationRequest} when the app is no longer in the
 *  foreground.
 */
public class LocationUpdatesBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "LUBroadcastReceiver";

    private String previousLoc = "", currentLoc = "";

    static final String ACTION_PROCESS_UPDATES = "com.thunderx.locationapp.ACTION_PROCESS_UPDATES";

    static final String ACTION_BOOT_UPDATES = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATES.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);

                if (result != null) {
                    List<Location> locations = result.getLocations();
                    LocationResultHelper locationResultHelper = new LocationResultHelper(
                            context, locations);

                    previousLoc = LocationResultHelper.getSavedLocationResult(context);

                    // Save the location data to SharedPreferences.
                    locationResultHelper.saveResults();

                    currentLoc = LocationResultHelper.getSavedLocationResult(context);

                    // Show notification with the location data.

                    Log.i(TAG, LocationResultHelper.getSavedLocationResult(context));
                    Log.i(TAG,  "" + previousLoc.equals(currentLoc));

                    // show notification only when location is changed
                    //if(!previousLoc.equals(currentLoc))
                        locationResultHelper.showNotification();
                }
            }
            else if(ACTION_BOOT_UPDATES.equals(intent.getAction()))
            {
                Log.i(TAG,  "Service Started");

                try {
                    Intent i = new Intent(context, LocationBackgroundService.class);
                    LocationBackgroundService.enqueueWork(context, i);

                    /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Intent i = new Intent(getApplicationContext(), LocationBackgroundService.class);
                        LocationBackgroundService.enqueueWork(getApplicationContext(), i);
                    }
                    else
                    {
                        Intent i = new Intent(MainActivity.this, LocationBackgroundService.class);
                        startService(i);
                    }*/
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }


}
