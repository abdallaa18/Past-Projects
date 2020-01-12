package com.example.experiment_1.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.Chat.ChatActivity;
import com.example.experiment_1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class EventActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView ename, edesc, edate, eprivacy,ehost,address;
    private Button chat;
    private GoogleMap mMap;
    private double lat, lng;
    private String locality;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/Get/";
    private String eventID;
    private  String username;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        ehost = findViewById(R.id.host);
        eprivacy = findViewById(R.id.privateOrPublic);
        edate = findViewById(R.id.eventDate);
        edesc = findViewById(R.id.eventDescription);
        ename = findViewById(R.id.eventName);
        chat = findViewById(R.id.chatButton);
        address = findViewById(R.id.addressInput);


        if(getIntent().getExtras() != null){
            ename.setText(getIntent().getStringExtra("eventName"));
            edesc.setText(getIntent().getStringExtra("eventDesc"));
            Log.d("Event Desc before", edesc.getText().toString());
            edate.setText(getIntent().getStringExtra("eventDate"));
            ehost.setText(getIntent().getStringExtra("eventHost"));
            eprivacy.setText(getIntent().getStringExtra("privacy") + " event.");
            username = getIntent().getStringExtra("userName");
            eventID = getIntent().getStringExtra("Event ID");
        }
        mQueue = Volley.newRequestQueue(EventActivity.this);
        Log.d("Event ID", String.valueOf(eventID));
        url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/Get/" + String.valueOf(eventID);


                JsonObjectRequest eventLocation = new JsonObjectRequest(
                        Request.Method.GET,
                        url,

                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    lat = response.getDouble("lat");
                                    lng = response.getDouble("log");


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }

                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                mQueue.add(eventLocation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(EventActivity.this);
            }  }, 500);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventActivity.this, ChatActivity.class);
                i.putExtra("Event ID", eventID);
                i.putExtra("userName", username);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng ll = new LatLng(lat, lng);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.addMarker(new MarkerOptions().position(ll).title(ename.getText().toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 19.0f));
    }
}
