package com.example.experiment_1.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.experiment_1.R;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private Button nextButton;
    private EditText firstName;
    private EditText lastName;
    private  EditText email;
    private  EditText zipCode;
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = (EditText) findViewById(R.id.fNameText);
        lastName = (EditText) findViewById(R.id.lNameText);
        email = (EditText) findViewById(R.id.emailText);
        zipCode = (EditText) findViewById(R.id.zipCodeText);
        nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidName(firstName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid First Name", Toast.LENGTH_LONG).show();
                }
                if (!isValidName(lastName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Last Name", Toast.LENGTH_LONG).show();
                }
                if (!isValidZip(zipCode.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Zip Code", Toast.LENGTH_LONG).show();
                }
                if (!isValidEmail(email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Email", Toast.LENGTH_LONG).show();

                }
                if (isValidName(firstName.getText().toString()) && isValidName(lastName.getText().toString())
                        && isValidEmail(email.getText().toString()) && isValidZip(zipCode.getText().toString())) {
                    Intent i = new Intent(RegisterActivity.this, Register2Activity.class);
                    i.putExtra("fName", firstName.getText().toString());
                    i.putExtra("lName", lastName.getText().toString());
                    i.putExtra("email", email.getText().toString());
                    i.putExtra("ZipCode", zipCode.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

    /**
     * Returns false if name is false, 0 length, or if it has a space
     * @param name
     * @return true/false
     */
    public static boolean isValidName(String name){
        if(name == null){
            return false;
        }
        if(name.length() == 0){
            return false;
        }
        char[] nameArray = name.toCharArray();

        for(int i = 0; i < nameArray.length; i++){
            if(nameArray[i] == ' '){
                return false;
            }
        }



        return true;
    }

    /**
     * Return false if zip is null, empty, if length is not 5, or if it has a space
     * @param zip
     * @return true/false
     */
    public static boolean isValidZip(String zip){
            if(zip == null){
                return false;
            }
        char[] zipArray = zip.toCharArray();

        for(int i = 0; i < zipArray.length; i++){
            if(zipArray[i] == ' '){
                return false;
            }
        }

        if(zip.length() != 5){
            return false;
        }

        return true;
    }

    /**
     * Returns false if email is null or doesn't meet the pattern
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email){
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
