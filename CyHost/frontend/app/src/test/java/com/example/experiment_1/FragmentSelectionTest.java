package com.example.experiment_1;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FragmentSelectionTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void dashboardFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "Dashboard";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "Dashboard";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "Dashboard");
    }

    @Test
    public void searchFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "Search";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "Search";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "Search");
    }

    @Test
    public void createEventFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "Create";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "Create";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "Create an Event");
    }

    @Test
    public void codeFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "Code";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "Code";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "QR Code");
    }

    @Test
    public void settingsFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "Settings";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "Settings";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "Settings");
    }

    @Test
    public void errorFragmentTest() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        fragHandler handler = mock(fragHandler.class);
        HomeActivity testAdmin = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String fragName = "okjkj";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "sddsd";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(fragName)).thenReturn(response);

        Assert.assertEquals(testAdmin.whichFragment(fragName,handler), "Error");
    }
}
