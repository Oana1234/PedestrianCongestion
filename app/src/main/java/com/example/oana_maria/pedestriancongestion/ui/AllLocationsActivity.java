package com.example.oana_maria.pedestriancongestion.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oana_maria.pedestriancongestion.R;
import com.example.oana_maria.pedestriancongestion.helpers.GetRequest;
import com.example.oana_maria.pedestriancongestion.models.Location;
import com.example.oana_maria.pedestriancongestion.models.LocationsList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class AllLocationsActivity extends AppCompatActivity  {

    List<Location> finalLocations;

    private static String url_all_locations = "http://18.196.61.10/get_locations2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_locations);

        finalLocations = new ArrayList<>();

        new GetDataFromServer().execute();

    }

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

                    System.out.println("LOCATIONS" + '\n');
                    for (int i = 0; i < nrLocations; i++) {

                        Location location = locationList.locations.get(i);
                        System.out.println(location.getLatitude());
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

            for (int i =0; i< finalLocations.size(); i++){
                System.out.println(finalLocations.get(i).getTimeStamp());
                System.out.print("\n");
            }
        }
    }
}

