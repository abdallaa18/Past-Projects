package com.example.experiment_1.DeleteEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeleteUserEventActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/";
    static String userName;
    private RecyclerView recyclerView;
    private DeleteUserEventAdapter adapter;

    List<DeleteUserEvent> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user_event);

        if(getIntent().getExtras() != null){
            userName = getIntent().getStringExtra("deleteUserName");

            url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/" + userName;
        }
        eventList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.deleteUserEventRecyclerView);
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
                                String eventHost = newEvent.getString("username");
                                Boolean privacy = newEvent.getBoolean("privacy");
                                String privacyStatus;

                                    privacyStatus = "";
                                    eventList.add(
                                            new DeleteUserEvent(
                                                    eventName,
                                                    eventId,
                                                    eventDescription,
                                                    eventDate,
                                                    eventHost,
                                                    privacyStatus

                                            )
                                    );
                                }







                            adapter = new DeleteUserEventAdapter(getApplicationContext(), eventList);
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
