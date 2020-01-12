package com.example.experiment_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.example.experiment_1.FriendRequestAddEvent.Friend;
import com.example.experiment_1.FriendRequestAddEvent.FriendAdapter;
import com.example.experiment_1.FriendRequestAddEvent.FriendRequest;
import com.example.experiment_1.FriendRequestAddEvent.FriendRequestAdapter;
import com.example.experiment_1.FriendRequestAddEvent.FriendSearchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RequestQueue mQueue;
    private RequestQueue mQueue2;

    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/";
    private String url2 = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/";

    private EditText userName;
    private String username;
    private Button searchButton;

    private List<FriendRequest> requestList;
    private RecyclerView requestView;
    private FriendRequestAdapter requestAdapter;

    private List<Friend> friendList;
    private RecyclerView friendView;
    private FriendAdapter friendAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_search, container,
                false);


        if(getActivity().getIntent().getExtras() != null){
            username = getActivity().getIntent().getStringExtra("userName");
            url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/" + username;
           url2 = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/" + username;
        }

    searchButton = rootView.findViewById(R.id.searchButton);
    userName = (EditText) rootView.findViewById(R.id.userNameSearch);
    requestList = new ArrayList<>();
    friendList = new ArrayList<>();

    requestView = (RecyclerView) rootView.findViewById(R.id.friendRequestRecyclerView);
    requestView.setHasFixedSize(true);
    requestView.setLayoutManager(new LinearLayoutManager(getActivity()));




        mQueue2 = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("URL", url);
                        try{

                           for(int i = 0; i < response.length(); i++){
                                JSONObject newFriendRequests = response.getJSONObject(i);
                                String friendUserName = newFriendRequests.getString("username");
                               String friendFirstName = newFriendRequests.getString("firstname");
                                String friendLastName = newFriendRequests.getString("lastname");

                                String friendName = friendFirstName + " " + friendLastName;

                                requestList.add(
                                        new FriendRequest(friendName, friendUserName)
                                );

                                requestAdapter = new FriendRequestAdapter(getActivity(), requestList);
                                requestView.setAdapter(requestAdapter);
                            }



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
        mQueue2.add(jsonArrayRequest2);

        friendView = (RecyclerView) rootView.findViewById(R.id.friendListRecyclerView);
        friendView.setHasFixedSize(true);
        friendView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                                JSONObject newFriendList = response.getJSONObject(i);
                                String friendUserName = newFriendList.getString("username");
                                String friendFirstName = newFriendList.getString("firstname");
                                String friendLastName = newFriendList.getString("lastname");

                                String friendName = friendFirstName + " " + friendLastName;

                                friendList.add(
                                        new Friend(friendName, friendUserName

                                        )
                                );

                                friendAdapter = new FriendAdapter(getActivity(), friendList);
                                friendView.setAdapter(friendAdapter);
                            }



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



    searchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(getActivity(), FriendSearchActivity.class);
            i.putExtra("searchName", userName.getText().toString() );
            i.putExtra("userName", username);
            startActivity(i);



        }
    });



        return rootView;


    }

}
