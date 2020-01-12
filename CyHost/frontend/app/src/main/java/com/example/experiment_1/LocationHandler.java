package com.example.experiment_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class LocationHandler extends AppCompatActivity {

    public LocationHandler()
    {

    }

    public String getLocality(String Address) {
        return null;
    }

    public String getLat(String Address){return null;}

    public String validAddress(String Address){return null;}
}
