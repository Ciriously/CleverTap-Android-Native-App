package com.example.clever_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.clevertap.android.sdk.CleverTapAPI;
import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_activity);
        TextView link = findViewById(R.id.login_link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Link Pressed...");
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

        // TEXT INPUTS
        EditText full_name = findViewById(R.id.full_name_enter);
        EditText email_id = findViewById(R.id.email_enter);
        EditText city = findViewById(R.id.city_enter);
        EditText phone = findViewById(R.id.phone_enter);
        EditText password = findViewById(R.id.password_enter);
        EditText identity = findViewById(R.id.identity_enter);
        Button registerBtn = findViewById(R.id.signupBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // USER PROFILE
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", full_name.getText().toString());
                profileUpdate.put("Identity", Integer.parseInt(identity.getText().toString()));
                profileUpdate.put("Email", email_id.getText().toString());
                profileUpdate.put("Phone", phone.getText().toString());
                profileUpdate.put("City", city.getText().toString());
                profileUpdate.put("Password", password.getText().toString());
                profileUpdate.put("MSG-email", false);        // Disable email notifications
                profileUpdate.put("MSG-push", true);          // Enable push notifications
                profileUpdate.put("MSG-sms", false);          // Disable SMS notifications
                profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications

                // Clevertap onUserLogin Function
                cleverTapAPI.onUserLogin(profileUpdate);

                // Profile Created
                Toast.makeText(getApplicationContext(), "Registration Successful!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

    }
}