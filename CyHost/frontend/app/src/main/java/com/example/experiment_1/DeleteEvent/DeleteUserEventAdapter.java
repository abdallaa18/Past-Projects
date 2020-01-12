package com.example.experiment_1.DeleteEvent;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.experiment_1.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteUserEventAdapter extends RecyclerView.Adapter<DeleteUserEventAdapter.DeleteUserEventViewHolder> {

    private Context mCtx;
    private List<DeleteUserEvent> eventList;
    private String privacyStatus;
    private  int eventID;
    public DeleteUserEventAdapter(Context mCtx, List<DeleteUserEvent> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public DeleteUserEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.delete_user_events_list, null);
        return new DeleteUserEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteUserEventViewHolder holder, int position) {
        DeleteUserEvent event = eventList.get(position);

        holder.eventTitle.setText(event.getEventName());
        holder.eventDesc.setText(event.getEventDescription());
        holder.eventHost.setText(event.getHost());
        holder.eventDate.setText(event.getEventDate());
        privacyStatus = event.getPrivacy();
        eventID = event.getId();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class DeleteUserEventViewHolder extends RecyclerView.ViewHolder {
        public View view;
        private ImageView eventImage;
        private TextView eventTitle, eventDesc, eventHost, eventDate;
        private Button deleteUserEvent;
        private RequestQueue mQueue;

        public DeleteUserEventViewHolder(@NonNull final View itemView) {

            super(itemView);
            view = itemView;
            eventImage = itemView.findViewById(R.id.deleteEventImage);
            eventTitle = itemView.findViewById(R.id.deleteEventTitle);
            eventDesc = itemView.findViewById(R.id.deleteEventDesc);
            eventHost = itemView.findViewById(R.id.deleteEventHost);
            eventDate = itemView.findViewById(R.id.deleteEventDate);
            deleteUserEvent = itemView.findViewById(R.id.deleteUserEventButton);

            //be able to press on each event
           deleteUserEvent.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String url = "http://coms-309-nv-3.misc.iastate.edu:8080/Event/delete/" + eventID;

                   mQueue = Volley.newRequestQueue(mCtx);

                   StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                           new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {

                                    Intent i = new Intent(mCtx, DeleteUserEventActivity.class);

                                    i.putExtra("deleteUserName", DeleteUserEventActivity.userName);
                                    mCtx.startActivity(i);
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Intent i = new Intent(mCtx, DeleteUserEventActivity.class);
                           i.putExtra("deleteUserName", DeleteUserEventActivity.userName);
                           mCtx.startActivity(i);
                           error.printStackTrace();
                       }
                   }

                   ) {
                       @Override
                       public Map<String, String> getHeaders() throws AuthFailureError {
                           Map<String, String>  params = new HashMap<String, String>();
                           params.put("Content-Type", "application/json");
                           params.put("Accept", "application/json");

                           return params;
                       }
                   };;
                   mQueue.add(dr);
               }
           });
               }

        }

}