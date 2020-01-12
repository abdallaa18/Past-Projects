package com.example.experiment_1.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.R;

public class Register2Activity extends AppCompatActivity {
    private Button submitButton;
    private String firstName;
    private String lastName;
    private String email;
    private String zipCode;
    private EditText confirmPassword;
    private EditText username;
    private EditText password;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/User/new/";
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mQueue = Volley.newRequestQueue(this);

        if (getIntent().getExtras() != null) {
            firstName = getIntent().getStringExtra("fName");
            lastName = getIntent().getStringExtra("lName");
            email = getIntent().getStringExtra("email");
            zipCode = getIntent().getStringExtra("ZipCode");
        }
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        confirmPassword = (EditText) findViewById(R.id.confirmText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidPassword(password.getText().toString(), confirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please ensure passwords match!", Toast.LENGTH_LONG).show();
                }
                if (!isValidUsername(username.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a username with atleast 5 characters", Toast.LENGTH_LONG).show();
                }
                if (isValidPassword(password.getText().toString(), confirmPassword.getText().toString()) && isValidUsername(username.getText().toString())) {
                    url = "http://coms-309-nv-3.misc.iastate.edu:8080/User/new/" + username.getText().toString() + "/" +
                            password.getText().toString() + "/" + firstName + "/" + lastName + "/" + email + "/" + zipCode;
                    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), "User Successfully Registered!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Register2Activity.this, HomeActivity.class);
                            i.putExtra("userName", username.getText().toString());
                            i.putExtra("Previous", "DashBoard");                            startActivity(i);

                            mQueue.stop();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            mQueue.stop();
                        }
                    });

                    mQueue.add(stringRequest);

                }
    }

});

    }

    /**
     * Returns false if the password and confirm password are not the same, if either are null, or if either have a space
     * @param checkPassword
     * @param checkConfirmPassword
     * @return true/false
     */
    public static boolean isValidPassword(String checkPassword, String checkConfirmPassword){
        if(checkPassword == null || checkConfirmPassword == null){
            return false;
        }
        if(!checkPassword.equals(checkConfirmPassword)){
            return false;
        }

        for(int i = 0; i < checkPassword.length(); i++){
            if(checkPassword.charAt(i) == ' '){
                return false;
            }
        }

        for(int i = 0; i < checkConfirmPassword.length(); i++){
            if(checkConfirmPassword.charAt(i) == ' '){
                return false;
            }
        }

        if(checkPassword.length() < 5){
            return false;
        }
        return true;
    }

    /**
     * Returns false if checkUserName is null, if it is empty, or less than 5 characters, or if it has a space
     * @param checkUserName
     * @return True/False
     */
    public static boolean isValidUsername(String checkUserName){
        if(checkUserName == null){
            return false;
        }
        if(checkUserName.trim().length() == 0){
            return false;
        }
        if(checkUserName.length() < 5){
            return false;
        }
        for(int i = 0; i < checkUserName.length(); i++){
            if(checkUserName.charAt(i) == ' '){
                return false;
            }
        }

        return true;
    }
}

