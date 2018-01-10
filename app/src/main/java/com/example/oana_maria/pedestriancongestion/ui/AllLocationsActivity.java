package com.example.oana_maria.pedestriancongestion.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.oana_maria.pedestriancongestion.JSONParser;
import com.example.oana_maria.pedestriancongestion.models.Location;
import com.example.oana_maria.pedestriancongestion.R;
import com.example.oana_maria.pedestriancongestion.RecyclerViewAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AllLocationsActivity extends AppCompatActivity implements RecyclerViewAdapter.OnLocationClickedListener{

    RecyclerView recyclerView;
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    List<Location> locationsList;
    private static String url_all_locations = "http://18.196.61.10/get_locations2.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LOCATIONS = "locations";
    private static final String TAG_PID = "pid";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_TIME_STAMP = "time_stamp";

    // products JSONArray
    JSONArray locations = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_locations);

        recyclerView =findViewById(R.id.rv_locations_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        // Loading products in Background Thread
        new LoadAllLocations().execute();

    }

    @Override
    public void onLocationClicked(Location location) {
    }

    class LoadAllLocations extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllLocationsActivity.this);
            pDialog.setMessage("Loading locations. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_locations, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Locations: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    locations = json.getJSONArray(TAG_LOCATIONS);
                    locationsList = new ArrayList<Location>();
                    // looping through All Products
                    for (int i = 0; i < locations.length(); i++) {
                        JSONObject c = locations.getJSONObject(i);

                        // Storing each json item in variable
                        Double latitude = c.getDouble(TAG_LATITUDE);
                        Double longitude = c.getDouble(TAG_LONGITUDE);
                        String time_stamp = c.getString(TAG_TIME_STAMP);
                        Long pid = c.getLong(TAG_PID);

                        // creating new HashMap
                       Location location = new Location();
                       location.pid=pid;
                       location.latitude= latitude;
                       location.longitude= longitude;
                       location.time_stamp=time_stamp;
                       locationsList.add(location);

                    }
                } else {

                    Intent i = new Intent(getApplicationContext(),
                            NewLocationActivity.class);

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(AllLocationsActivity.this);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.updateLocations(locationsList);
                }
            });

        }
    }
}

