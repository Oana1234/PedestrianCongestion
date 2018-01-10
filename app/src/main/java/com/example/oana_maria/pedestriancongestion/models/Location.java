package com.example.oana_maria.pedestriancongestion.models;

/**
 * Created by Oana-Maria on 06/01/2018.
 */

public class Location {

    public long pid;
    public Double latitude;
    public Double longitude;
    public String time_stamp;

    public Location(){}

    public Location(long pid, Double latitude, Double longitude, String time_stamp) {
        this.pid = pid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time_stamp = time_stamp;
    }

    public long getPid() {
        return pid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getTime_stamp() {
        return time_stamp;
    }
}
