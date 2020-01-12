package com.example.experiment_1;

import com.example.experiment_1.Chat.ChatActivity;
import com.example.experiment_1.Chat.ChatHandler;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void joinChatTest_success() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        ChatHandler handler = mock(ChatHandler.class);
        ChatActivity testChatJoin = new ChatActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String usernameCorrect = "abdallaa";
        int eventID = 156;
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
        when(handler.joinChat(usernameCorrect, eventID)).thenReturn(response);

        Assert.assertEquals(testChatJoin.join(usernameCorrect,eventID,handler),usernameCorrect + " has joined the chat!");
    }

    @Test
    public void joinChatTest_failed() throws JSONException {
        //This creates a Mock Object of the class that we have not fully implemented
        ChatHandler handler = mock(ChatHandler.class);
        ChatActivity testChatJoin = new ChatActivity();

        //These are the parameters to the function that we have not yet implemented in the class LoginHandler
        String usernameCorrect = "abdallaa";
        int incorrectEventID = 156;

        //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
        String response;

    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
    with a boolean value "true" because the login was a success
     */
        response = "error";

    /*This line specifies the behavior of the getResponse method from LoginHandler that currently returns null
        You can think of it as overriding the behavior of the function and forcing it to return a specific value
        In the following line, we are forcing this unimplemented method to return our predefined variable "response"
     */
        when(handler.joinChat(usernameCorrect, incorrectEventID)).thenReturn(response);

        Assert.assertEquals(testChatJoin.join(usernameCorrect,incorrectEventID,handler),"Error");
    }


}
