package com.example.oana_maria.pedestriancongestion.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oana_maria.pedestriancongestion.JSONParser;
import com.example.oana_maria.pedestriancongestion.R;
import com.example.oana_maria.pedestriancongestion.models.DoubleNameValuePair;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewLocationActivity extends AppCompatActivity {

    Button btn_add_location;
    EditText edt_lat, edt_long;

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();


    private static String url_create_product = "http://18.196.61.10/new_location.php";

    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        btn_add_location= findViewById(R.id.btn_add_location);
        edt_lat = findViewById(R.id.edt_lat);
        edt_long = findViewById(R.id.edt_long);

        btn_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddNewLocation().execute();
            }
        });
    }


    class AddNewLocation extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewLocationActivity.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            Double latitude = Double.parseDouble(edt_lat.toString());
            Double longitude = Double.parseDouble(edt_long.toString());

            // Building Parameters
            List<DoubleNameValuePair> params = new ArrayList<DoubleNameValuePair>();
            params.add(new DoubleNameValuePair("latitude", latitude));
            params.add(new DoubleNameValuePair("longitude", longitude));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), AllLocationsActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}
