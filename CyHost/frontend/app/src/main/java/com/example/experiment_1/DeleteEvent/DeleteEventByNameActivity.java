package com.example.experiment_1.DeleteEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeleteEventByNameActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DeleteEventByNameAdapter deleteUserAdapter;
    private List<DeleteEventByName> deleteList;
    private EditText search;
    private Button searchButton;
    private RequestQueue mQueue;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/all/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event_by_name);


        searchButton = (Button) findViewById(R.id.deleteEventButton);
        search = (EditText) findViewById(R.id.deleteEventSearch);
        deleteList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.deleteEventNameRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/all/" + search.getText().toString();

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
                                        deleteList.add(
                                                new DeleteEventByName(
                                                        eventName,
                                                        eventId,
                                                        eventDescription,
                                                        eventDate,
                                                        eventHost,
                                                        privacyStatus

                                                )
                                        );
                                    }







                                    deleteUserAdapter = new DeleteEventByNameAdapter(getApplicationContext(), deleteList);
                                    recyclerView.setAdapter(deleteUserAdapter);

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
        });




    }
}
