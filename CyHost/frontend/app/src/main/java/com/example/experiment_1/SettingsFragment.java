package com.example.experiment_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.DeleteEvent.DeleteEventByNameActivity;
import com.example.experiment_1.DeleteEvent.DeleteUsersActivity;
import com.example.experiment_1.LoginRegister.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsFragment extends Fragment {
    private Button logOut, deleteEvents, deleteUsers;
    private String userName;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/User/isAdmin/";
    private RequestQueue mQueue;
    private String isAdmin ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container,
                false);
        logOut = rootView.findViewById(R.id.logOut);
        deleteEvents = rootView.findViewById(R.id.deleteEvents);
        deleteUsers = rootView.findViewById(R.id.deleteUsers);
        if(getActivity().getIntent().getExtras() != null) {
            userName = getActivity().getIntent().getStringExtra("userName");
        }
        url = "http://coms-309-nv-3.misc.iastate.edu:8080/User/isAdmin/" + userName;


            mQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest adminCheck = new JsonObjectRequest(Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getBoolean("admin")){
                                    deleteEvents.setVisibility(View.VISIBLE);
                                    deleteEvents.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent i = new Intent(getActivity(), DeleteEventByNameActivity.class);
                                            startActivity(i);
                                        }
                                    });
                                    deleteUsers.setVisibility(View.VISIBLE);

                                    deleteUsers.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent i = new Intent(getActivity(), DeleteUsersActivity.class);
                                            startActivity(i);
                                        }
                                    });

                                }
                                else{

                                    deleteEvents.setVisibility(View.GONE);
                                    deleteUsers.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mQueue.stop();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    mQueue.stop();
                }
            });

            mQueue.add(adminCheck);

Log.d("isAdmin2", isAdmin + "whyyyy");


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getIntent().removeExtra("userName");
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        });
        return rootView;
    }
    public boolean isAdmin(String username, AdminHandler adminHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (adminHandler.getResponse(username).equals("true")) {
            return true;
        }
        else {

            return false;
        }
    }
}
