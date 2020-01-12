package com.example.experiment_1;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class NotificationTest {

        @Rule
        public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void notificationTest_returnsPending() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        NotificationHandler handler = mock(NotificationHandler.class);
        HomeActivity notificationSuccess = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String notifyUsername = "abdallaa";


        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "yes";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(notifyUsername)).thenReturn(response);

        Assert.assertEquals(notificationSuccess.notify(notifyUsername, handler), "You have pending friend requests!");

    }
    @Test
    public void notificationTest_returnsNo() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        NotificationHandler handler = mock(NotificationHandler.class);
        HomeActivity notificationSuccess = new HomeActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String notifyUsername = "ahmad55";


        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "no";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(notifyUsername)).thenReturn(response);

        Assert.assertEquals(notificationSuccess.notify(notifyUsername, handler), "");

    }
}
