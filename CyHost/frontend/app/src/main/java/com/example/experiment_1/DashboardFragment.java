package com.example.experiment_1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.Event.Event;
import com.example.experiment_1.Event.EventAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private RequestQueue mQueue;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/Members";
    private String userName;

    private RecyclerView recyclerView;
    private EventAdapter adapter;

    private List<Event> eventList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container,
                false);
        if(getActivity().getIntent().getExtras() != null){
            userName = getActivity().getIntent().getStringExtra("userName");

            url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/Members/" + userName;
        }

            eventList = new ArrayList<>();
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





        mQueue = Volley.newRequestQueue(getActivity());
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
                                }
                                else{
                                    privacyStatus = "Private";
                                }

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

                            adapter = new EventAdapter(getActivity(), eventList);
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


        return rootView;
    }
}
