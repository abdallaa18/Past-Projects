package com.example.experiment_1.FriendRequestAddEvent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;
import com.example.experiment_1.SearchFragment;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder> {



    private Context mCtx;
    private List<FriendRequest> friendRequestList;

    public FriendRequestAdapter(Context mCtx, List<FriendRequest> friendRequestList) {
        this.mCtx = mCtx;
        this.friendRequestList = friendRequestList;
    }
    @NonNull
    @Override
    public FriendRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.friends_request_list, null);

        return new FriendRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestViewHolder holder, int position) {
        FriendRequest friendRequest = friendRequestList.get(position);

        holder.requesteeName.setText(friendRequest.getRequesteeName());
        holder.requesteeUserName.setText(friendRequest.getRequesteeUserName());

    }

    @Override
    public int getItemCount() {
        return friendRequestList.size();
    }

    class FriendRequestViewHolder extends RecyclerView.ViewHolder {
        private TextView requesteeName;
        private TextView requesteeUserName;
        private Button acceptRequest, denyRequest;
        private RequestQueue mQueue;
        private  RequestQueue mQueue2;

        String deleteURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/Delete/";
        public FriendRequestViewHolder(@NonNull final View itemView) {
            super(itemView);

            requesteeName = itemView.findViewById(R.id.requesteeName);
            requesteeUserName = itemView.findViewById(R.id.requesteeUserName);
            acceptRequest = itemView.findViewById(R.id.acceptRequest);
            denyRequest = itemView.findViewById(R.id.denyRequest);





            mQueue2 = Volley.newRequestQueue(mCtx);




            denyRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = MainActivity.username;
                    String fromUsername = requesteeUserName.getText().toString();
                    final String deleteURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/Delete/" + username + "/" + fromUsername;
                    final String acceptURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/Accept/" + username + "/" + fromUsername;
                    Log.d("accept", acceptURL);
                    Log.d("decline", deleteURL);
                    mQueue = Volley.newRequestQueue(mCtx);

                    StringRequest dr = new StringRequest(Request.Method.DELETE, deleteURL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent i = new Intent(mCtx, SearchFragment.class);
                                    mCtx.startActivity(i);

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }

                    );
                    mQueue.add(dr);
                }
            });

acceptRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String username = MainActivity.username;
        String fromUsername = requesteeUserName.getText().toString();
        final String deleteURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/Delete/" + username + "/" + fromUsername;
        final String acceptURL = "http://coms-309-nv-3.misc.iastate.edu:8080/Friend/Request/Accept/" + username + "/" + fromUsername;
        Log.d("accept", acceptURL);
        Log.d("decline", deleteURL);
        StringRequest dr2 = new StringRequest(Request.Method.DELETE, acceptURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }

        );
        mQueue2.add(dr2);
    }
});


        }
    }

}
