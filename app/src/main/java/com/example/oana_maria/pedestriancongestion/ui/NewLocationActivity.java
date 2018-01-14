package com.example.oana_maria.pedestriancongestion.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.oana_maria.pedestriancongestion.R;
import com.example.oana_maria.pedestriancongestion.helpers.PostRequest;
import java.io.IOException;
import java.net.URLEncoder;

public class NewLocationActivity extends AppCompatActivity {

    Button btn_add_location;
    EditText edt_lat, edt_long;

    private static String url_insert_location = "http://18.196.61.10/new_location.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        btn_add_location = findViewById(R.id.btn_add_location);
        edt_lat = findViewById(R.id.edt_lat);
        edt_long = findViewById(R.id.edt_long);

        btn_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostDataTOServer().execute();
            }
        });
    }

    private class PostDataTOServer extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {


            String latitude = edt_lat.getText().toString();
            String longitude = edt_long.getText().toString();

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

            return null;
        }
    }
}
