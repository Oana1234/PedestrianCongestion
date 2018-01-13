package com.example.oana_maria.pedestriancongestion.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.oana_maria.pedestriancongestion.R;

public class NewLocationActivity extends AppCompatActivity {

    Button btn_add_location;
    EditText edt_lat, edt_long;

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

            }
        });
    }


}
