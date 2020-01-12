package com.example.experiment_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class CreateEventFragment extends Fragment {
    //Event Instance Variables
    private Switch booleanSwitch;
    private  EditText ename;
    private  EditText edesc;
    private  DatePicker edate;
    private String[] ListElements = new String[]{};
    private   ListView listview;
    private Button Addbutton;
    private ImageView eventImage;

    //Map Instance Variables
    private Button AddressGo;
    private Double lat, lng;
    private MapView mMapView;
    private EditText addressString;
     String locality;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    //Miscellaneous
    private Button createEventButton;
    private boolean check;
    private String username;
    private RequestQueue mQueue;
    private Uri imageUri;
    private EditText GetValue;
    private static final int PICK_IMAGE = 100;
    private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/new";
    private JSONObject event;
    private JSONObject holder;
     String address;

    @Nullable
    @Override
    /**
     * Creates a new fragment in which you can create a class.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_create_event, container,
                false);
        super.onCreate(savedInstanceState);


        if(getActivity().getIntent().getExtras() != null) {
            username = getActivity().getIntent().getStringExtra("userName");
        }
        createEventButton = rootView.findViewById(R.id.finishCreateEvent);
        eventImage = rootView.findViewById(R.id.chooseImage);
        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        booleanSwitch = rootView.findViewById(R.id.booleanSwitch);
        listview = rootView.findViewById(R.id.memberList);
        Addbutton = rootView.findViewById(R.id.itemButton);
        GetValue = rootView.findViewById(R.id.memberAdd);

        final List < String > ListElementsArrayList = new ArrayList <>
                (Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(),
                android.R.layout.simple_list_item_1 ,ListElementsArrayList);
        listview.setAdapter(adapter);
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ListElementsArrayList.contains(GetValue.getText().toString())){
                    ListElementsArrayList.remove(GetValue.getText().toString());
                    adapter.notifyDataSetChanged();
                }
                else {

                    ListElementsArrayList.add(GetValue.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        addressString = rootView.findViewById(R.id.addressInput);

        AddressGo = rootView.findViewById(R.id.setAddress);
        AddressGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = addressString.getText().toString();

                try {
                    Log.d("We got in" , "test");
                    initGoogleMap(savedInstanceState);


                } catch (IOException e) {
                    Log.d("caught" , "test");
                    e.printStackTrace();
                }
            }
        });
        booleanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.d("Switch text",booleanSwitch.getText().toString() );
                    if(booleanSwitch.getText().toString().contains("Event is PRIVATE")){
                        booleanSwitch.setText("Event is PUBLIC");


                    }

                }
                else{
                    booleanSwitch.setText("Event is PRIVATE");

                }
            }
        });


        Log.d("Log", String.valueOf(lng));
        Log.d("Lat", String.valueOf(lat));
    createEventButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mQueue = Volley.newRequestQueue(getActivity());
            check = booleanSwitch.isChecked();
            ename = rootView.findViewById(R.id.ename);
            edesc = rootView.findViewById(R.id.description);
            edate = rootView.findViewById(R.id.date);

            if(ename.getText().toString().trim().isEmpty() || edesc.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity().getBaseContext(), "Event Name or Event Description are empty", Toast.LENGTH_LONG).show();
            }
            else {

                try {

                    Log.d("Lat", String.valueOf(lat));
                    JSONObject eventInfo = new JSONObject();
                    eventInfo.put("eventName", ename.getText().toString());
                    eventInfo.put("eventDescription", edesc.getText().toString());
                    //MAP OUTPUT TO STORE
//                    eventInfo.put("eventAddress", addressString);
                    eventInfo.put("privacy", check);
                    eventInfo.put("username", username);
                    eventInfo.put("location", address);
                    eventInfo.put("locality", locality);
                    eventInfo.put("log", lng);
                    eventInfo.put("lat", lat);
                    Date selectedDate = new Date(edate.getYear() -1900, (edate
                            .getMonth()), edate.getDayOfMonth());
                    String strDate = null;
                    SimpleDateFormat dateFormatter = new SimpleDateFormat(
                            "MM-dd-yyyy");
                    strDate = dateFormatter.format(selectedDate);
                    JSONObject holder = new JSONObject();
                    holder.put("event", eventInfo);
                    holder.put("uDate",strDate);
                    //MAP OUTPUT TO STORE
//                    holder.put("Long", lng);
//                    holder.put("Lat",lat);
//                    holder.put("locality",locality);
                    JSONArray array = new JSONArray();

                    for(int i = 0; i < ListElementsArrayList.size(); i++){
                        array.put(ListElementsArrayList.get(i));
                    }
                    holder.put("invitees", array);
                    final String mRequestBody = holder.toString();
                    Log.d("JSON", mRequestBody);


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity().getBaseContext(), "Event successfully created", Toast.LENGTH_LONG).show();

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new CreateEventFragment()).commit();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };
                    mQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    });
        return rootView;
    }

    private void openGallery(){
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
super.onActivityResult(requestCode,resultCode,data);
if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
    imageUri = data.getData();
    eventImage.setImageURI(imageUri);
}
    }


    public void initGoogleMap(Bundle savedInstanceState) throws IOException {

        Geocoder gc = new Geocoder(getActivity());
        List<Address> list = gc.getFromLocationName(address, 1);
        if (list != null && list.size() > 0) {
            Address add = list.get(0);
            locality = add.getLocality();
            lat = add.getLatitude();
            lng = add.getLongitude();
            Toast.makeText(getActivity().getBaseContext(), locality , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getActivity().getBaseContext(), "Location not found", Toast.LENGTH_LONG).show();
        }
        Bundle mapViewBundle = null;
        if(savedInstanceState != null)
        {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

    }

    public String findLocality(String addr, LocationHandler locH) throws JSONException {
        if (locality != null) {
            return locality;
        }
        return "false";
    }

    public String getLat(String addr, LocationHandler locH) throws JSONException {
        if (locH.getLocality("addr").equals("true")) {
            return "" + lat;
        }
        return "0";
    }

    public String validAddress(String addr, LocationHandler locH) throws JSONException {
        if (addr != null) {
            return "true";
        }
        return "false";
    }
}
