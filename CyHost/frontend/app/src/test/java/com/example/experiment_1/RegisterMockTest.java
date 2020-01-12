package com.example.experiment_1;

import android.content.ComponentName;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.experiment_1.LoginRegister.Register2Activity;
import com.example.experiment_1.LoginRegister.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 27)
public class RegisterMockTest {
    private RegisterActivity activity;
    private Register2Activity activity2;

    private static final String firstName = "Nathan";
    private static final String lastName = "Bretz";
    private static final String email = "smitra@iastate.edu";
    private static final String zipCode = "50014";





    @Mock
    private Network mockedNetwork;


    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(RegisterActivity.class).create().resume().get();
        MockitoAnnotations.initMocks(this);
        activity2 = Robolectric.buildActivity(Register2Activity.class).create().resume().get();
    }

    @Test
    public void shouldNotBeNull(){
        assertNotNull(activity);
        assertNotNull(activity2);
    }
    @Test
    public void defaultValues(){
        EditText firstName = activity.findViewById(R.id.fNameText);
        EditText lastName = activity.findViewById(R.id.lNameText);
        EditText email = activity.findViewById(R.id.emailText);
        EditText zip = activity.findViewById(R.id.zipCodeText);
        Button next = activity.findViewById(R.id.nextButton);

        assertEquals("Next", next.getText().toString());
        assertEquals("",firstName.getText().toString());
        assertEquals("",lastName.getText().toString());
        assertEquals("",email.getText().toString());
        assertEquals("",zip.getText().toString());

    }
    @Test
    public void buttonClick(){
        EditText firstNameText = activity.findViewById(R.id.fNameText);
        EditText lastNameText = activity.findViewById(R.id.lNameText);
        EditText emailText = activity.findViewById(R.id.emailText);
        EditText zipText = activity.findViewById(R.id.zipCodeText);
        Button next = activity.findViewById(R.id.nextButton);

       firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        emailText.setText(email);
        zipText.setText(zipCode);

        next.performClick();
        ShadowActivity.IntentForResult shadowActivity = shadowOf(activity).getNextStartedActivityForResult();
        ComponentName componentName = shadowActivity.intent.getComponent();
        String name = componentName.flattenToShortString();
        assertEquals("com.example.experiment_1/.LoginRegister.Register2Activity", name);
//test
        Bundle extras = shadowActivity.intent.getExtras();
        assertTrue(extras.containsKey("fName"));
        assertTrue(extras.getString("fName").length() > 0);
        assertTrue(extras.containsKey("lName"));
        assertTrue(extras.getString("lName").length() > 0);
        assertTrue(extras.containsKey("email"));
        assertTrue(extras.getString("email").length() > 0);
        assertTrue(extras.containsKey("ZipCode"));
        assertTrue(extras.getString("ZipCode").length() > 0);
        shadowOf(activity.getMainLooper()).idle();
    }
    @Test
    public void finalRegister() throws VolleyError, InterruptedException {

        EditText username = activity2.findViewById(R.id.usernameText);
        EditText pass = activity2.findViewById(R.id.passwordText);
        EditText confirm = activity2.findViewById(R.id.confirmText);
        Button submit = activity2.findViewById(R.id.submitButton);

        RequestController mQueue = RequestController.getInstance(activity2.getApplicationContext());
        mQueue.setTestMode(true, mockedNetwork);
        String correctUsername = "spongebob";

        byte[] mData = ("New Account " + correctUsername + " Registered").getBytes();

        when(mockedNetwork.performRequest(Mockito.any(Request.class))).thenReturn(new NetworkResponse(mData));
        username.setText(correctUsername);
        pass.setText("squarepants");
        confirm.setText("squarepants");

submit.performClick();
Thread.sleep(500);
mQueue.setTestMode(false);
String actual = new String(mData);
        assertEquals("New Account spongebob Registered", actual);


    }
}
