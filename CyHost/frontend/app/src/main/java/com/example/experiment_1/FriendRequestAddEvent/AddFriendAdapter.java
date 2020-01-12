package com.example.experiment_1.FriendRequestAddEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendAdapter.AddFriendViewHolder> {



    private Context mCtx;
    private List<AddFriend> addFriendList;

    public AddFriendAdapter(Context mCtx, List<AddFriend> addFriendList) {
        this.mCtx = mCtx;
        this.addFriendList = addFriendList;
    }
    @NonNull
    @Override
    public AddFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.add_friend_list, null);

        return new AddFriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFriendViewHolder holder, int position) {
        AddFriend addFriend = addFriendList.get(position);

        holder.addFriendName.setText(addFriend.getAddFriendName());
        holder.addFriendUserName.setText(addFriend.getAddFriendUserName());

    }

    @Override
    public int getItemCount() {
        return addFriendList.size();
    }

    class AddFriendViewHolder extends RecyclerView.ViewHolder {
        private TextView addFriendName;
        private TextView addFriendUserName;
        private  Button addFriend;
        private String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/add";
        private RequestQueue mQueue;
        public AddFriendViewHolder(@NonNull View itemView) {
            super(itemView);

            addFriendName = itemView.findViewById(R.id.addFriendName);
            addFriendUserName = itemView.findViewById(R.id.addFriendUsername);
            addFriend = itemView.findViewById(R.id.addFriendButton);



                addFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fromUserName = MainActivity.username;
                        String toUserName = addFriendUserName.getText().toString();

                        mQueue = Volley.newRequestQueue(mCtx);
                        JSONObject friendRequest = new JSONObject();

                        try {
                            friendRequest.put("to", toUserName);
                            friendRequest.put("from", fromUserName);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        final String mRequestBody = friendRequest.toString();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                addFriendList.clear();
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

                    }
                });
        }
    }

}
