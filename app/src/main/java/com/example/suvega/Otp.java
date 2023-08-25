package com.example.suvega;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Otp extends AppCompatActivity {
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify);
        verify=findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Otp.this, "Successfully Verified", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Otp.this, HomeScreenMapView.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
