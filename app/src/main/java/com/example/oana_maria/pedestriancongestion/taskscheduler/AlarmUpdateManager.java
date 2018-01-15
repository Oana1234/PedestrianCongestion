package com.example.oana_maria.pedestriancongestion.taskscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.os.AsyncTask;

import com.example.oana_maria.pedestriancongestion.helpers.PostRequest;

import android.location.Location;
import android.os.Bundle;

import com.example.oana_maria.pedestriancongestion.ui.SplashActivity;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Oana-Maria on 15/01/2018.
 */

public class AlarmUpdateManager extends BroadcastReceiver implements LocationListener {
    Location alarmManagerLocation;

    public AlarmUpdateManager() {
    }

    public AlarmUpdateManager(Location fromActivity) {
        this.alarmManagerLocation = fromActivity;
    }

    private static String url_insert_location = "http://18.196.61.10/new_location.php";

    public void set(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent;
        Intent myIntent;

        myIntent = new Intent(context, AlarmUpdateManager.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (1000 * 5), pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                myFunction();
            }
        }).start();
    }

    private void myFunction() {
        if (alarmManagerLocation != null) {
            String latitude = String.valueOf(alarmManagerLocation.getLatitude());
            String longitude = String.valueOf(alarmManagerLocation.getLongitude());

            try {
                String data = URLEncoder.encode("latitude", "UTF-8")
                        + "=" + URLEncoder.encode(latitude, "UTF-8");

                data += "&" + URLEncoder.encode("longitude", "UTF-8") + "="
                        + URLEncoder.encode(longitude, "UTF-8");


                PostRequest example = new PostRequest();
                example.doPostRequest(url_insert_location, data);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        alarmManagerLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
