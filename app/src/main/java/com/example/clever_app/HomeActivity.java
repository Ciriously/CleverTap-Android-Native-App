package com.example.clever_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.InAppNotificationButtonListener;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements CTInboxListener, DisplayUnitListener, InAppNotificationButtonListener {
    private CleverTapAPI api;
    private Button inbox_button, push_button, inapp_button, logout_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.home_activity);

        // CLEVERTAP INITIALIZATION
        api = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);

        //Set the Notification Inbox Listener
        api.setCTNotificationInboxListener(this);
        //Initialize the inbox and wait for callbacks on overridden methods
        api.initializeInbox();

        //Set the In App Listener
        api.setInAppNotificationButtonListener(this);

        // NATIVE DISPLAY UNITS
        api.setDisplayUnitListener(this);
        api.getAllDisplayUnits();

        inbox_button = findViewById(R.id.inbox_btn);
        push_button = findViewById(R.id.push_btn);
        inapp_button = findViewById(R.id.inapp_btn);
        logout_button = findViewById(R.id.logout_btn);

        // PUSH BUTTON
        push_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger an Product Viewed Event
                api.pushEvent("Product Viewed");
                Toast.makeText(getApplicationContext(), "Product Viewed Event Triggered!", Toast.LENGTH_SHORT).show();
            }
        });

        // IN-APP BUTTON
        inapp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger an Charged Event
                api.pushEvent("Charged");
                Toast.makeText(getApplicationContext(), "Charged Event Triggered!!", Toast.LENGTH_SHORT).show();
            }
        });

        // LOG OUT BUTTON
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logged Out!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });

        //NATIVE DISPLAY EVENT BUTTON
        Button native_display_button = findViewById(R.id.native_display_btn);
        native_display_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Native Display Event!!", Toast.LENGTH_SHORT).show();
                api.pushEvent("Native Display");
            }
        });

        // STORIES BUTTON
        Button stories_button = findViewById(R.id.stories_btn);
        stories_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, StoriesActivity.class));
            }
        });

        Button multi_value_button = findViewById(R.id.multi_value_btn);
        multi_value_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("sdkmultivalDummy", "");
                api.pushProfile(profileUpdate);
            }
        });
    }

    // APP INBOX FUNCTIONS
    @Override
    public void inboxDidInitialize() {
        inbox_button.setOnClickListener(v -> {
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("Promotions");
            tabs.add("Offers");
            CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
            styleConfig.setFirstTabTitle("All Messages");
            styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
            styleConfig.setTabBackgroundColor("#FFFFFF");           // White background for tabs
            styleConfig.setSelectedTabIndicatorColor("#F04444");    // Red indicator for selected tab
            styleConfig.setSelectedTabColor("#F04444");             // Red text for selected tab
            styleConfig.setUnselectedTabColor("#333333");           // Dark gray text for unselected tabs
            styleConfig.setBackButtonColor("#000000");              // Black color for back button
            styleConfig.setNavBarTitleColor("#000000");             // Black title color for navbar
            styleConfig.setNavBarTitle("Inbox");                    // Title for navbar
            styleConfig.setNavBarColor("#FFFFFF");                  // White background for navbar
            styleConfig.setInboxBackgroundColor("#EFEFEF");         // Light gray background for inbox
            if (api != null) {
                api.showAppInbox(styleConfig); //With Tabs
            }
        });
    }

    @Override
    public void inboxMessagesDidUpdate() {

    }

    // NATIVE DISPLAY LISTENER FUNCTION
    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        for (int i = 0; i <units.size() ; i++) {
            CleverTapDisplayUnit unit = units.get(i);
            setNativeDisplay(unit);
        }
    }

    // CUSTOM IN APP BUTTON HANDLER
    @Override
    public void onInAppButtonClick(HashMap<String, String> hashMap) {
        if(hashMap != null){
            System.out.println("InApp Button Click!!");
            System.out.println("Button Text: "+hashMap.get("text"));
            if (Objects.equals(hashMap.get("action"), "open_url")){
                String url = hashMap.get("url");
                System.out.println("Opening URL");
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
            if (Objects.equals(hashMap.get("action"), "goto_activity")){
                String activity = hashMap.get("activity");
                System.out.println("Opening Activity");
                if(Objects.equals(activity, "Cart")){
                    Toast.makeText(getApplicationContext(), "Product Added to Cart!", Toast.LENGTH_SHORT).show();
                }
                // Other activity
                else{

                }
            }
        }
    }

    // CUSTOM NATIVE DISPLAY FUNCTION
    public void setNativeDisplay(CleverTapDisplayUnit unit){
        System.out.println("Unit ID: " + unit.getUnitID());
        ArrayList<CleverTapDisplayUnitContent> contents = unit.getContents();
        for (CleverTapDisplayUnitContent content : contents) {
            String title = content.getTitle();
            String message = content.getMessage();
            TextView native_title = findViewById(R.id.native_title1);
            TextView native_msg = findViewById(R.id.native_msg1);
            native_title.setVisibility(View.VISIBLE);
            native_msg.setVisibility(View.VISIBLE);
            native_title.setText(title);
            native_msg.setText(message);
            /*
            LinearLayout customViewLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.native_display, null);
            TextView native_title = customViewLayout.findViewById(R.id.native_title);
            TextView native_msg = customViewLayout.findViewById(R.id.native_msg);
            ImageView native_img = customViewLayout.findViewById(R.id.native_img);
            native_title.setText(title);
            native_msg.setText(message);
             */
        }
    }
}