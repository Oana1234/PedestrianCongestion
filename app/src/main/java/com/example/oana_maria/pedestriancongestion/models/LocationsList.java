package com.example.oana_maria.pedestriancongestion.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Oana-Maria on 13/01/2018.
 */

public class LocationsList {

    @SerializedName("locations")
    @Expose
    public List<Location> locations = null;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }



}
