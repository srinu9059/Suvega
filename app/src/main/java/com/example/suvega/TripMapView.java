package com.example.suvega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TripMapView extends AppCompatActivity {
    Button buttonTripDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_map_view);
        buttonTripDetails = findViewById(R.id.button_trip_details);
        buttonTripDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripMapView.this,TripDetails.class);
                startActivity(intent);
            }
        });
    }
}
