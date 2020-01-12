package com.example.experiment_1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

public class HomeActivity extends AppCompatActivity {
    private String previous;
    private String username;

    private WebSocketClient cc;
    private String url = "ws://coms-309-nv-3.misc.iastate.edu:8080/Notification/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.notification);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if(getIntent().getExtras() != null){
            previous = getIntent().getStringExtra("Previous");
            username = getIntent().getStringExtra("userName");


        }
        Draft[] drafts = {new Draft_6455()};


        url = "ws://coms-309-nv-3.misc.iastate.edu:8080/Notification/"+ username;

        Log.d("Notification url", url);

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(url),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("Message", "run() returned: " + message);
                    final String nMessage = message;

                if(nMessage.equals("yes")) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mp.start();
                            Toast.makeText(HomeActivity.this, "You have pending friend requests!", Toast.LENGTH_LONG).show();
                            Log.d("Notification Message", nMessage);
                        }
                    });
                }
                }
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();


        if(previous.equals("Setting")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingsFragment()).commit();

        }
        else if(previous.equals("Search")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId()){
                        case R.id.navigation_dashboard:

                            selectedFragment = new DashboardFragment();
                            break;

                        case R.id.navigation_search:
                            selectedFragment = new SearchFragment();
                            break;

                        case R.id.navigation_create_event:
                            selectedFragment = new CreateEventFragment();
                            break;

                        case R.id.navigation_qr_code:
                            selectedFragment = new QrCodeFragment();
                            break;

                        case R.id.navigation_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public String notify(String username, NotificationHandler notificationHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (notificationHandler.getResponse(username).equals("yes")) {
            return "You have pending friend requests!";
        }
        else {

            return "";
        }
    }
    public String whichFragment(String fragName, fragHandler fragHandler){
        if(fragHandler.getResponse(fragName).equals("Dashboard")){
            return "Dashboard";
        }
        else if(fragHandler.getResponse(fragName).equals("Search")) {
            return "Search";
        }  else if(fragHandler.getResponse(fragName).equals("Create")) {
            return "Create an Event";
        }   else if(fragHandler.getResponse(fragName).equals("Code")) {
            return "QR Code";
        }else if(fragHandler.getResponse(fragName).equals("Settings")) {
            return "Settings";
        }
        else{
            return "Error";
        }
    }
}

