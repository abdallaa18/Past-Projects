package com.example.experiment_1.DeleteEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeleteUsersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DeleteUserAdapter deleteUserAdapter;
    private List<DeleteUser> deleteList;
    private EditText search;
    private Button searchButton;
    private RequestQueue preLimQueue;
    private String deleteFullName;
    String preLimURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/lite/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_users);



            search = (EditText) findViewById(R.id.deleteUsernameSearch);
            searchButton = (Button) findViewById(R.id.deleteUserNameButton);
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deleteList = new ArrayList<>();

                    preLimURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/lite/" + search.getText().toString();


                    preLimQueue =  Volley.newRequestQueue(DeleteUsersActivity.this);

                    JsonObjectRequest userInfo = new JsonObjectRequest(
                            Request.Method.GET,
                            preLimURL,
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try{
                                        String deleteUserLast = response.getString("lastname");
                                        String deleteUserFirst = response.getString("firstname");
                                        String deleteUserName = response.getString("username");

                                        deleteFullName = deleteUserFirst + " " + deleteUserLast;


                                        recyclerView = (RecyclerView)findViewById(R.id.deleteUsersRecyclerView);
                                        recyclerView.setHasFixedSize(true);
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                                        recyclerView.setLayoutManager(linearLayoutManager);

                                        deleteList.add(
                                                new DeleteUser(deleteFullName, deleteUserName)
                                        );
                                        deleteUserAdapter = new DeleteUserAdapter(getApplicationContext(), deleteList);
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
                    preLimQueue.add(userInfo);
                    search.setText("");

                }
            });


    }
}
