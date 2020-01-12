package com.example.experiment_1.DeleteEvent;


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
import com.example.experiment_1.R;

import java.util.List;

public class DeleteUserAdapter extends RecyclerView.Adapter<DeleteUserAdapter.DeleteUserViewHolder> {



    private Context mCtx;
    private List<DeleteUser> deleteUserList;

    public DeleteUserAdapter(Context mCtx, List<DeleteUser> deleteUserList) {
        this.mCtx = mCtx;
        this.deleteUserList = deleteUserList;
    }
    @NonNull
    @Override
    public DeleteUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.delete_users_list, null);

        return new DeleteUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteUserViewHolder holder, int position) {
        DeleteUser deleteUser = deleteUserList.get(position);

        holder.deleteName.setText(deleteUser.getDeleteName());
        holder.deleteUserName.setText(deleteUser.getDeleteUserName());

    }

    @Override
    public int getItemCount() {
        return deleteUserList.size();
    }

    class DeleteUserViewHolder extends RecyclerView.ViewHolder {
        private TextView deleteName;
        private  TextView deleteUserName;
        private RequestQueue mQueue;


        Button viewEvents, deleteUser;
        public DeleteUserViewHolder(@NonNull View itemView) {
            super(itemView);

            deleteName = itemView.findViewById(R.id.deleteName);
            deleteUserName = itemView.findViewById(R.id.deleteUserName);
            viewEvents = itemView.findViewById(R.id.viewUserEvents);
            deleteUser = itemView.findViewById(R.id.deleteUser);

            viewEvents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mCtx, DeleteUserEventActivity.class);
                    i.putExtra("deleteUserName", deleteUserName.getText().toString());
                    mCtx.startActivity(i);

                }
            });
            deleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQueue = Volley.newRequestQueue(mCtx);
                    String url = "http://coms-309-nv-3.misc.iastate.edu:8080/User/delete/" + deleteUserName.getText().toString();

                  Log.d("DELETE USER URL", url);

                    StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent i = new Intent(mCtx, DeleteUsersActivity.class);
                                    mCtx.startActivity(i);

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent i = new Intent(mCtx, DeleteUsersActivity.class);
                            mCtx.startActivity(i);
                            error.printStackTrace();
                        }
                    }

                    );
                    mQueue.add(dr);
                }
            });

//            removeFriend.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String userName = MainActivity.username;
//                    String secondUserName = friendUserName.getText().toString();
//                    String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Friends/" + userName + "/" + secondUserName;
//
//                    mQueue = Volley.newRequestQueue(mCtx);
//
//                    StringRequest dr = new StringRequest(Request.Method.DELETE, url,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//
//
//                                }
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            error.printStackTrace();
//                        }
//                    }
//
//                    );
//                    mQueue.add(dr);
//                }
//
//
//
//            });
        }
    }

}
