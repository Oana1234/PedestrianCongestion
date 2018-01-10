package com.example.oana_maria.pedestriancongestion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oana_maria.pedestriancongestion.R;

public class MainActivity extends AppCompatActivity {

    Button button_all_locations, button_new_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_all_locations = findViewById(R.id.all_locations);
        button_new_location = findViewById(R.id.new_location);

        button_new_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), NewLocationActivity.class);
                startActivity(intent1);
            }
        });

        button_all_locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), AllLocationsActivity.class);
                startActivity(intent2);
            }
        });

    }
}
