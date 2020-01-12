package com.example.experiment_1.FriendRequestAddEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.Event.Event;
import com.example.experiment_1.Event.EventAdapter;
import com.example.experiment_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendEventsActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/";
    private String userName;

    private RecyclerView recyclerView;
    private EventAdapter adapter;

    private List<Event> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_events);

        if(getIntent().getExtras() != null){
            userName = getIntent().getStringExtra("friendUserName");

            url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/" + userName;
        }

        eventList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.friendEventRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);


        mQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("URL", url);
                        try{

                            for(int i = 0; i < response.length(); i++){
                                JSONObject newEvent = response.getJSONObject(i);
                                String eventName = newEvent.getString("eventName");
                                String eventDescription = newEvent.getString("eventDescription");
                                String eventDate = newEvent.getString("date");
                                int eventId = newEvent.getInt("eventID");
                                String stringifiedEventID = String.valueOf(eventId);
                                String eventHost = newEvent.getString("username");
                                Boolean privacy = newEvent.getBoolean("privacy");
                                String privacyStatus;
                                if(privacy){
                                    privacyStatus = "Public";
                                    eventList.add(
                                            new Event(
                                                    eventName,
                                                    stringifiedEventID,
                                                    eventDescription,
                                                    eventDate,
                                                    eventHost,
                                                    privacyStatus

                                            )
                                    );
                                }
                                else{
                                    privacyStatus = "Private";
                                }




                            }

                            adapter = new EventAdapter(getApplicationContext(), eventList);
                            recyclerView.setAdapter(adapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }

                }
        );

        // Add JsonArrayRequest to the RequestQueue
        mQueue.add(jsonArrayRequest);

    }
}
