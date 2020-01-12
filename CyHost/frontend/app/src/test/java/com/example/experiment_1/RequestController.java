package com.example.experiment_1;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;

public class RequestController {
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    @SuppressLint("StaticFieldLeak")
    private static RequestController mInstance;
    private FakeRequestQueue fakeRequestQueue;
    private boolean testMode;
    private Network mNetwork;

    private RequestController(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
        testMode = false;
        fakeRequestQueue = null;
        mNetwork = null;


    }
public static synchronized RequestController getInstance(Context context){
        if(mInstance == null){
            mInstance = new RequestController(context);
        }
        return  mInstance;
}
private RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            mRequestQueue.start();
        }
    if (testMode) {
        return fakeRequestQueue;
    }
    else{
        return mRequestQueue;
    }
}

public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);

}
public void setFakeNetworkResposne(String response){
        if(!testMode){
            setTestMode(true);
        }
    ((FakeNetwork)mNetwork).setDataToReturn(response.getBytes(StandardCharsets.UTF_8));
}
public void setTestMode(boolean testMode){
        Network network;
        if(testMode){
            network = new FakeNetwork();
        }else{
            network = null;
        }
        setTestMode(testMode, network);
}
public void setTestMode(boolean testMode, Network network){
        this.testMode = testMode;
        mNetwork = network;
        if(testMode){
            mRequestQueue.stop();
            fakeRequestQueue = new FakeRequestQueue(network);
            fakeRequestQueue.start();
        }
        else{
            fakeRequestQueue.stop();
            mNetwork = null;
            fakeRequestQueue = null;
        }
}
}
class FakeRequestQueue extends RequestQueue{
    public FakeRequestQueue(Network network){
        super(new NoCache(),network,1,new ExecutorDelivery(new Executor() {
            @Override
            public void execute(Runnable runnable) {
                runnable.run();
            }
        }));
    }
}
class FakeNetwork implements Network{
    private byte[] dataToReturn = null;

    public void setDataToReturn(byte[] data){
        dataToReturn = data;
    }
    @Override
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        return new NetworkResponse(dataToReturn);
    }
}
