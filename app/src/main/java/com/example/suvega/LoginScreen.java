package com.example.suvega;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    TextInputLayout textInputLayout;
    TextView tvRegister ,forgotPassword;
    Button btn ;
    @Override
    protected void onCreate(Bundle hello) {
        super.onCreate(hello);
        setContentView(R.layout.login_screen);
        tvRegister = findViewById(R.id.tv3);
        textInputLayout=findViewById(R.id.textInputLayout_email);
        forgotPassword = findViewById(R.id.tvForgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginScreen.this,Forgot_password.class);
                startActivity(intent);
                finish();
            }

        });
        btn = findViewById(R.id.btnSignin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,Register.class);
                startActivity(intent);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
