package com.example.oana_maria.pedestriancongestion.maps;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.oana_maria.pedestriancongestion.R;
import com.example.oana_maria.pedestriancongestion.helpers.GetRequest;
import com.example.oana_maria.pedestriancongestion.models.Location;
import com.example.oana_maria.pedestriancongestion.models.LocationsList;
import com.example.oana_maria.pedestriancongestion.ui.AllLocationsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Location> finalLocations;
    ArrayList<LatLng> listCoordinates;

    private static String url_all_locations = "http://18.196.61.10/get_locations2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        finalLocations = new ArrayList<>();
        listCoordinates = new ArrayList<>();

        new MapsActivity.GetDataFromServer().execute();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }
    //START

    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {

                GetRequest example = new GetRequest();
                String response = example.run(url_all_locations);

                Gson gson = new GsonBuilder().create();

                LocationsList locationList = gson.fromJson(response, LocationsList.class);

                if (locationList != null) {

                    int nrLocations = locationList.locations.size();

                    for (int i = 0; i < nrLocations; i++) {

                        Location location = locationList.locations.get(i);
                        finalLocations.add(location);
                    }
                } else System.out.println("Eroare la mapare");


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            for (int i = 0; i < finalLocations.size(); i++) {
                System.out.println(finalLocations.get(i).getTimeStamp());
                double lat = Double.parseDouble(finalLocations.get(i).getLatitude());
                double lng = Double.parseDouble(finalLocations.get(i).getLongitude());
                listCoordinates.add(new LatLng(lat, lng));
            }

            addHeatMap();

        }
    }

        private void addHeatMap() {

        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(listCoordinates)
                .build();

         mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

    }


}