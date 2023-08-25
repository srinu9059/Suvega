package com.example.suvega;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    private static final String ACCOUNT_SID = "AC4fdc6c46fda7a9076589298d6e002eab";
    private static final String AUTH_TOKEN = "5559eefe54b7229cb9998863fe772c37";
    private TextInputLayout etfullName,etmobileNumber,etpassword,etconfirmPassword,etmail;
    Button btnSignUp;
    CheckBox terms;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME="userDetails";
    private static final String KEY_NAME="name";
    private static final String KEY_MOBILE="mobileNumber";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_CONFIRMPASSWORD="confirmPassword";
    TextView signIn;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fo_register);
        terms=findViewById(R.id.btnTerms);
        btnSignUp= findViewById(R.id.btnSignup);
        signIn = findViewById(R.id.tvSignin);
        etfullName=findViewById(R.id.etFullName);
        etmobileNumber = findViewById(R.id.etMobileNumber);
        etmail=findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etPassword);
        etconfirmPassword = findViewById(R.id.etConfirmPassword);

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Otp.class);
                startActivity(intent);
//                String phoneNumber = etmobileNumber.getEditText().toString();
//                if (!phoneNumber.isEmpty()) {
//                    sendSms(phoneNumber);
//                }
//
//                finish();
//                saveUserDetails();
//                if (editor!=null){
//                    Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
//                     intent = new Intent(Register.this,Login_Screen.class);
//                    startActivity(intent);
//                    finish();
//                }
            }
//            private void saveUserDetails() {
//                String name=etfullName.getText().toString().trim();
//                String mobilNumber=etmobileNumber.getText().toString().trim();
//                String password=etpassword.getText().toString().trim();
//                String confirmPassword=etconfirmPassword.getText().toString().trim();
//                String email=etmail.getText().toString().trim();
//                boolean termsAccept=terms.isChecked();
//                if(name.isEmpty()){
//                    etfullName.setError("please Enter your name");
//                    etfullName.requestFocus();
//                    return;
//                }
//                if(mobilNumber.isEmpty()){
//                    etmobileNumber.setError("please Enter your phone number");
//                    etmobileNumber.requestFocus();
//                    return;
//                }
//                if(password.isEmpty()){
//                    etpassword.setError("password required");
//                    etpassword.requestFocus();
//                    return;
//                }
//                if(confirmPassword.isEmpty()){
//                        etconfirmPassword.setError("password should match or empty");
//                        etconfirmPassword.requestFocus();
//                        return;
//
//                }
//                if(!termsAccept){
//                    terms.setError("Accept terms and conditions");
//                    terms.requestFocus();
//                    return;
//                }
//                editor=sharedPreferences.edit();
//                editor.putString(KEY_NAME,name);
//                editor.putString(KEY_MOBILE,mobilNumber);
//                editor.putString(KEY_PASSWORD,password);
//                editor.putString(KEY_CONFIRMPASSWORD,confirmPassword);
//                editor.putString(KEY_EMAIL,email);
//                editor.apply();
//
//            }
        });
    }
//    private void sendSms(final String phoneNumber) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
//
//                    String fromPhoneNumber = "9059623175";
//                    String messageText = "Hello from Twilio!";
//
////                    Message.creator(
////                            new PhoneNumber(phoneNumber),
////                            new PhoneNumber(fromPhoneNumber),
////                            messageText
////                    ).create(client);
////
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Handle UI updates or responses here
////                            Account message;
////                            System.out.println("Message SID: " + message.getSid());
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Handle any errors or exceptions here
//                        }
//                    });
//                }
//            }
//        }).start();
//    }

}
