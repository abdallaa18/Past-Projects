package com.example.experiment_1.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.R;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private Button registerText;
  private TextView logInMessage;
  private RequestQueue mQueue;

    private  String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/logIn/";

    public static String username;
    private EditText uName;
    private EditText pWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logInMessage = (TextView) findViewById(R.id.message);
        b1 = (Button) findViewById(R.id.logIn);
        registerText = (Button) findViewById(R.id.register);
        uName = (EditText) findViewById(R.id.userName);
        pWord = (EditText) findViewById(R.id.password);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.drums);
        mp.start();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!isValid(uName.getText().toString())){
                    Toast.makeText(getApplicationContext(),  "Username can not be Empty", Toast.LENGTH_LONG).show();

                }

                else if(!isValid(pWord.getText().toString())){
                    Toast.makeText(getApplicationContext(),  "Password can not be Empty", Toast.LENGTH_LONG).show();

                }
                else {

                    uName = (EditText) findViewById(R.id.userName);
                    pWord = (EditText) findViewById(R.id.password);

                    mQueue = Volley.newRequestQueue(MainActivity.this);
                    url = "http://coms-309-nv-3.misc.iastate.edu:8080/Users/logIn/" + uName.getText().toString() + "/" + pWord.getText().toString();

                    Log.d("URL", url);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                if(response.equals("true")){
                                    username = uName.getText().toString();
                                   Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                   i.putExtra("userName", uName.getText().toString());
                                    i.putExtra("Previous", "DashBoard");
                                  startActivity(i);
                                }
                                else{
                                    logInMessage.setText("Wrong username or password");
                                }
                                mQueue.stop();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                                    logInMessage.setText("STILL NO");
                                    error.printStackTrace();
                                    mQueue.stop();
                        }
                    });

                    mQueue.add(stringRequest);
                }
            }
        });
        mQueue = Volley.newRequestQueue(MainActivity.this);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getApplicationContext(),  "You are being redirected to register!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    public String tryLogin(String username, String password, LoginHandler loginHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (loginHandler.getResponse(username, password).equals("true")) {
            return "true";
        }

        return "false";
    }

    /**
     *
     * @param input
     * Checks if input is null, empty, or less than 5 characters. If any of the input has a white space it returns false.
     * @return if any of the above is met, it returns false.
     */
    public static boolean isValid(String input){
    if(input == null){
        return false;
    }
        if(input.trim().isEmpty())
            return false;
        char[] inputArray = input.toCharArray();
    if(input.length() < 5){
        return false;
    }
        for(int i = 0; i < inputArray.length; i++){
            if(inputArray[i] == ' '){
                return false;
            }
        }

        return true;
}
}

