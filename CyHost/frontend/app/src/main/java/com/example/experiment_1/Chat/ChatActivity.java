package com.example.experiment_1.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.experiment_1.HomeActivity;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity {

    private Button start;
    private TextView output;
    private WebSocketClient cc;
    private EditText message;
    private String eventID;
    private String username;
    private String url = "ws://coms-309-nv-3.misc.iastate.edu:8080/chat/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);
        if(getIntent().getExtras() != null){

            username = getIntent().getStringExtra("userName");
            eventID = getIntent().getStringExtra("Event ID");

        }

        start = (Button) findViewById(R.id.sendMessage);
        output = (TextView) findViewById(R.id.output);
        output.setMovementMethod(new ScrollingMovementMethod());
    message = (EditText) findViewById(R.id.message);

        Draft[] drafts = {new Draft_6455()};


        url = "ws://coms-309-nv-3.misc.iastate.edu:8080/chat/" + eventID + "/" + username;

        Log.d("websocket url", url);


        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(url),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s=output.getText().toString();
                    //t1.setText("hello world");
                    //Log.d("first", "run() returned: " + s);
                    //s=t1.getText().toString();
                    //Log.d("second", "run() returned: " + s);
                    if(message.contains(username + ":")){
                        output.setTextColor(Color.WHITE);

                    }


                    output.setText(s+"\n\n       "+message);

                }
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cc.send(message.getText().toString());
                    message.setText("");
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }



            }
        });


                    }


    public String join(String username, int eventID, ChatHandler chatHandler) throws JSONException {


        //Does not work because .getResponse has not been implemented
        if (chatHandler.joinChat(username,eventID).equals("true")) {
            return username + " has joined the chat!";
        }
        else {

            return "Error";
        }
    }
}
