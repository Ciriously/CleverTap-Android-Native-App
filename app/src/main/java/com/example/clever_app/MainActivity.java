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
import androidx.appcompat.app.AppCompatDelegate;
import com.clevertap.android.sdk.CleverTapAPI;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{
    private CleverTapAPI cleverTapAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CLEVERTAP INITIALIZATION
        cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);

        // LAYOUT
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.login_activity);

        // REQUEST NOTIFICATION PERMISSION
        //cleverTapAPI.promptForPushPermission(true);

        // MAIN CODE
        TextView link = findViewById(R.id.registration_link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Link Pressed...");
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        EditText full_name = findViewById(R.id.full_name_enter);
        EditText email_id = findViewById(R.id.email_enter);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login Button Pressed...");
                // USER PROFILE
                HashMap<String, Object> profileUpdate = new HashMap<>();
                profileUpdate.put("Name", full_name.getText().toString());
                profileUpdate.put("Phone", email_id.getText().toString());
                profileUpdate.put("MSG-email", true);        // Disable email notifications
                profileUpdate.put("MSG-push", true);          // Enable push notifications

                // Clevertap Profile Push Function
                cleverTapAPI.onUserLogin(profileUpdate);

                // Profile Created
                Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}
