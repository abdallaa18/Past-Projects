package com.example.experiment_1;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QRCodeTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void correctQRCode() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        QRCodeHandler handler = mock(QRCodeHandler.class);
        QrCodeFragment testQRCode = new QrCodeFragment();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String userName = "abdallaa";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "true";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(userName)).thenReturn(response);

        Assert.assertEquals(testQRCode.qrCode(userName,handler), userName);
    }

    @Test
    public void incorrectQRCode() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        QRCodeHandler handler = mock(QRCodeHandler.class);
        QrCodeFragment testQRCode = new QrCodeFragment();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String userName = "hsellars";

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "sadasd";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.getResponse(userName)).thenReturn(response);

        Assert.assertEquals(testQRCode.qrCode(userName,handler), "Error");
    }
}
