package com.example.experiment_1.FriendRequestAddEvent;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {



    private Context mCtx;
    private List<Friend> friendList;

    public FriendAdapter(Context mCtx, List<Friend> friendList) {
        this.mCtx = mCtx;
        this.friendList = friendList;
    }
    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.friends_list, null);

        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friendList.get(position);

        holder.friendName.setText(friend.getFriendName());
        holder.friendUserName.setText(friend.getFriendUserName());

    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        private TextView friendName;
        private TextView friendUserName;
        private RequestQueue mQueue;

        Button viewEvents, removeFriend;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.friendName);
            friendUserName = itemView.findViewById(R.id.friendUserName);
            viewEvents = itemView.findViewById(R.id.viewEventButton);
            removeFriend = itemView.findViewById(R.id.removeFriendButton);

            viewEvents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mCtx, FriendEventsActivity.class);
                    i.putExtra("friendUserName", friendUserName.getText().toString());
                    mCtx.startActivity(i);

                }
            });

            removeFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userName = MainActivity.username;
                    String secondUserName = friendUserName.getText().toString();
                    String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/" + userName + "/" + secondUserName;

                    mQueue = Volley.newRequestQueue(mCtx);

                    StringRequest dr = new StringRequest(Request.Method.DELETE, url,
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
                    mQueue.add(dr);
                }



            });
        }
    }

}
