package com.example.experiment_1.FriendRequestAddEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendSearchActivity extends AppCompatActivity {
    private RequestQueue preLimQueue;
    private String username;
    private String searchedName;
    private RequestQueue mQueue;
    private List<Friend> friendList;
    private String friendFullName;
    private RecyclerView friendView;
    private FriendAdapter friendAdapter;
    private EditText nameToBeSearched;
    private List<AddFriend> addFriendList;
    private RecyclerView addFriendView;
    private AddFriendAdapter addFriendAdapter;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/";

    private String preLimURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/lite/";

    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getIntent().getExtras() != null) {
            username = getIntent().getStringExtra("userName");
            searchedName = getIntent().getStringExtra("searchName");
            preLimURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/lite/" + searchedName;

        }

        Log.d("userName", username);
        Log.d("Searched", searchedName);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);
        searchButton = findViewById(R.id.searchScreenButton);
        nameToBeSearched = findViewById(R.id.searchResultSearch);
        friendList = new ArrayList<>();
        addFriendList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(FriendSearchActivity.this);
        url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/" + username + "/" + searchedName;
        preLimQueue =  Volley.newRequestQueue(FriendSearchActivity.this);

        JsonObjectRequest userInfo = new JsonObjectRequest(
                Request.Method.GET,
                preLimURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("URL", url);
                        try{
                            String friendLastName = response.getString("lastname");
                            String friendFirstName = response.getString("firstname");


                                friendFullName = friendFirstName + " " + friendLastName;





                        }catch (JSONException e) {
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
        preLimQueue.add(userInfo);
        Log.d("URL", url);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            addFriendList.clear();
                            friendList.clear();
                            friendView = findViewById(R.id.searchRecyclerView);
                            friendView.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            friendView.setLayoutManager(linearLayoutManager);

                            friendList.add(
                                    new Friend(friendFullName, "@" + searchedName)
                            );
                            friendAdapter = new FriendAdapter(getApplicationContext(), friendList);

                            friendView.setAdapter(friendAdapter);

                        } else {
                            friendList.clear();
                            addFriendList.clear();
                            addFriendView = findViewById(R.id.searchRecyclerView);
                            addFriendView.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            addFriendView.setLayoutManager(linearLayoutManager);

                            addFriendList.add(
                                    new AddFriend(friendFullName, searchedName)
                            );

                            addFriendAdapter = new AddFriendAdapter(getApplicationContext(), addFriendList);

                            addFriendView.setAdapter(addFriendAdapter);
                        }
                        mQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "True");
                        error.printStackTrace();
                        mQueue.stop();
                    }
                });

                mQueue.add(stringRequest);
            }
        }, 1000);


            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchedName = nameToBeSearched.getText().toString();

                    getIntent().putExtra("searchName", searchedName);
                 finish();
                 startActivity(getIntent());
                }
            });

    }
    public String sendRequest(String username,String username2, FriendSearchHandler friendSearchHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (friendSearchHandler.getResponse(username,username2).equals("true")) {
            return "Friend Request sent";
        }
        else {

            return "Friend Request Failed";
        }
    }
}